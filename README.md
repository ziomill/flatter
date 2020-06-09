# Flatter

A simple utility to flat an array of arbitrarily nested arrays.

## Build Image

Use [Docker](https://www.docker.com) to build a Container image of Flatter.
Build process will do:
1. Download and install OpenJDK : The JAVA environment to execute the Chat.
2. Download and install Maven   : To compile and build source code.
3. Copy locale source into docker image.
4. Do Maven Build, executing Integration Test Suite, and generating the executable Java archive.
5. Configure Launch Command and Container local Port.

```bash
docker build -f Dockerfile -t flatter:1.0.0 .
```
## Run Container

Use [Docker](https://www.docker.com) to run builded image as Container
Run process will start new Container, binding 10001 host port to 10001 Container port.

```bash
docker run --name flatter -i -d --rm -p 10000:10000 flatter:1.0.0
```
## Usage

An HTTP Server listens on:
* Port   : 10001
* URL    : http://localhost:10001/flat
* Method : POST
Use an HTTP Client to give it a try.

### Request example
```json
[
   1,
   2,
   3,
   4,
   [
      5,
      6
   ],
   7,
   [
      8,
      9,
      [
         10,
         [
            11,
            12,
            13,
            14,
            [
               15,
               16
            ]
         ]
      ]
   ]
]
```

![picture alt](https://i.ibb.co/84LMFwY/Chat-Screen.png "Chat Preview")

## License
[MIT](https://choosealicense.com/licenses/mit/)

