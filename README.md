# BigPay
## Architecture: MVP

## Language: Kotlin

## Libraries:
1. Dagger2
2. RxJava2
3. Retrofit2

## Modules:
1. app: contains Application class and main feature for application
2. base: contains BaseActivity class
3. data: contains data model
4. network: configurate network with retrofit2 and okhttp
5. service: configurate tiles and selection service
6. selection_provider: contains business logic for post service
7. tiles_provider: contains business logic for fetching tiles list

## Configurate:
1. Api host: the host is in appConfiguration.gradle file, under this code
```
ext.urlConfiguration = [
        host : "http://192.168.1.98:9235/"
]
```
So just update the host for connecting to your server.

## Features:
1. Fetching tiles list from api /tiles and show them into a recyclerView
2. Request post selection to server through /selection?id=<id_string> to receive a message from server


