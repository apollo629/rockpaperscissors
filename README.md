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
        	"winningScore": 3, //optional default:3
        	"gameType": "COMPUTER" //optional default:COMPUTER
        }
        Response: 
        {
            "status": "SUCCESS",
            "errorMessage": null,
            "id": "493e74e4-8edd-4e50-97f6-5c739a86d86b",
            "winningScore": 3,
            "gameType": "COMPUTER",
            "gameStatus": "IN_PROGRESS"
        }
  

### Retrieve Game
    GET http://localhost:8080/api/v1/games/{id}  
        Response: 
        {
            "status": "SUCCESS",
            "errorMessage": null,
            "id": "493e74e4-8edd-4e50-97f6-5c739a86d86b",
            "winningScore": 3,
            "gameType": "COMPUTER",
            "gameStatus": "IN_PROGRESS"
        }
        Fail Response example:
        {
            "status": "FAILURE",
            "errorMessage": "Game can not be found with id: 493e74e4-8edd-4e50-97f6-5c739a86d"
        }

### Play a Move
    POST http://localhost:8080/api/v1/games/{id}/play
        Request:
            { 
            	"move":"ROCK"
            }
        Response: 
            {
                "status": "SUCCESS",
                "errorMessage": null,
                "gameId": "493e74e4-8edd-4e50-97f6-5c739a86d86b",
                "roundDto": {
                    "roundResult": "BREAK",
                    "roundWinner": {
                        "playerType": "PERSON",
                        "score": 1
                    }
                },
                "player1": {
                    "type": "PERSON",
                    "move": "ROCK",
                    "score": 1
                },
                "player2": {
                    "type": "COMPUTER",
                    "move": "SCISSORS",
                    "score": 0
                },
                "gameStatus": "IN_PROGRESS",
                "winnerOfTheGame": null
            }
            
    * GameStatus will be 'FINISHED' after winning score is reached by any one of the players
        Finished Game Response:
            {
                "status": null,
                "errorMessage": null,
                "gameId": "493e74e4-8edd-4e50-97f6-5c739a86d86b",
                "roundDto": {
                    "roundResult": "BREAK",
                    "roundWinner": {
                        "playerType": "PERSON",
                        "score": 3
                    }
                },
                "player1": {
                    "type": "PERSON",
                    "move": "ROCK",
                    "score": 3
                },
                "player2": {
                    "type": "COMPUTER",
                    "move": "SCISSORS",
                    "score": 2
                },
                "gameStatus": "FINISHED",
                "winnerOfTheGame": {
                    "playerType": "PERSON",
                    "score": 3
                }
            } 
            
         Fail Response Example:
            {
                "status": "FAILURE",
                "errorMessage": "Game with idd814c209-71cb-46c7-bcff-a876a8263402is already finished. You cannot play another move at this game."
            }   
        
        