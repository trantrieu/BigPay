package main

import (
	"encoding/json"
	"fmt"
	"log"
	"math"
	"math/rand"
	"net/http"
	"strings"
	"time"
)

const MAX_TILES = 100
const MAX_LABEL = 80

var AVAIL = []rune("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789")

// This is *really* annoying - golang doesn't really do bigdecimal..

type Tile struct {
	Id       string
	Label    string
	Priority float64
}

type Global struct {
	tiles []Tile
}

func genLabel(nr int) string {
	var sb strings.Builder
	alen := len(AVAIL)
	for i := 0; i < nr; i = i + 1 {
		sb.WriteRune(AVAIL[rand.Intn(alen)])
	}
	return sb.String()
}

// Make the global context slightly neater.
func wrapMe(g *Global,
	f func(*Global, http.ResponseWriter, *http.Request)) func(
	http.ResponseWriter, *http.Request) {
	wrapper := func(w http.ResponseWriter, req *http.Request) {
		f(g, w, req)
	}
	return wrapper
}

func handleTileRetrieval(g *Global, w http.ResponseWriter, req *http.Request) {
	if req.Method != http.MethodGet {
		http.Error(w, "400 Bad request", http.StatusBadRequest)
		return
	}
	/* OK. Just invent and return the list of tiles */
	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode(g.tiles)

}

func handleSelection(g *Global, w http.ResponseWriter, req *http.Request) {
	if req.Method != http.MethodPost {
		http.Error(w, "400 Bad request", http.StatusBadRequest)
		return
	}
	vs, here := req.URL.Query()["id"]
	if !here {
		http.Error(w, "400 Bad request", http.StatusBadRequest)
		return
	}
	val := vs[0]
	found := false
	// Find the tile
	var lbl string

	for _, tile := range g.tiles {
		if tile.Id == val {
			/* Gotcha !*/
			lbl = tile.Label
			found = true
			break
		}
	}
	if !found {
		http.Error(w, "400 Bad request", http.StatusBadRequest)
		return
	}
	/* OK. Return a value */
	var result map[string]interface{}

	result = make(map[string]interface{}, 0)
	result["message"] = fmt.Sprintf("Well done - you selected item %s",
		lbl)
	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode(result)
}

func (gbl *Global) chooseTiles() {
	rand.Seed(time.Now().Unix())
	gbl.tiles = make([]Tile, rand.Intn(MAX_TILES))
	for idx, _ := range gbl.tiles {
		tile := &gbl.tiles[idx]

		tile.Id = fmt.Sprintf("%c{\"&Id%d}",
			AVAIL[rand.Intn(len(AVAIL))], idx)
		// Generate some suitably randomly long string
		label_len := rand.Intn(MAX_LABEL)
		tile.Label = genLabel(label_len)
		// grotty, and we should really do this more precisely,
		// but that involves defining our own types, and ugh.
		tile.Priority = math.Round(rand.Float64()*100.0) / 100.0
	}
}

func main() {
	gbl := new(Global)
	gbl.chooseTiles()
	http.HandleFunc("/tiles", wrapMe(gbl, handleTileRetrieval))
	http.HandleFunc("/selection", wrapMe(gbl, handleSelection))
	log.Println("Listening on port 9238")
	err := http.ListenAndServe(":9238", nil)
	if err != nil {
		panic("Cannot listen")
	}
	fmt.Println("All done")
}
