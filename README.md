# Implementation of RESTful API for a rock,paper,scissors game


#### Usage

> Prerequisite
- Maven 
- Java 8 or higher 

> Start the application

Through following command:

    mvn clean install spring-boot:run 
    
Maven will install all the dependencies and followed by run the application


## APIs
    POST /api/v1/games
    GET /api/v1/games/{id}
    POST /api/v1/games/{id}/play
    

## Examples

### Create Game 
    POST http://localhost:8080/api/v1/games
        Request: 
        {
        	"winningScore": 3,
        	"gameType": "COMPUTER" //optional
        }
        Response: 
        {
            "id": "8f2cfd4f-a033-4f61-afa7-8c7fc3b3224c",
            "winningScore": 3,
            "gameType": "COMPUTER",
            "gameStatus": "IN_PROGRESS",
            "status": null,
            "errorMessage": null
        }
  

### Retrieve Game
    GET http://localhost:8080/api/v1/games/{id}  
        Response: 
        {
            "id": "8f2cfd4f-a033-4f61-afa7-8c7fc3b3224c",
            "winningScore": 3,
            "gameType": "COMPUTER",
            "gameStatus": "IN_PROGRESS",
            "status": null,
            "errorMessage": null
        }

### Play a Move
    POST http://localhost:8080/api/v1/games/{id}/play
        Request:
            { 
            	"move":"ROCK"
            }
        Response: 
            {
                "gameId": "8f2cfd4f-a033-4f61-afa7-8c7fc3b3224c",
                "gameStatus": "IN_PROGRESS",
                "player1": {
                    "move": "ROCK",
                    "score": 0
                },
                "player2": {
                    "move": "PAPER",
                    "score": 1
                },
                "status": null,
                "errorMessage": null
            }
            
    gameStatus will be 'FINISHED' after winning score is reached by any one of the players
        