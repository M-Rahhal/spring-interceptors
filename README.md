# spring-interceptors
A Simple spring boot application to work with interceptors

1. The application has **THREE** endpoints which return nothing.
2. There is an interceptor that counts the number of requests that are sent to the first and the second endpoints together.
3. There is a thread that acts like a stopwatch to reset the number of requests within a minute.
4. The interceptor checks if the number of the requests reaches 10 then each request that comes within that minute will be delayed 10 seconds to achieve throttling.
5. The maximum throttling period, the delay duration and the maximum number of requests is added to the application properties file.
6. The third endpoint will never get affected.

## stress results:

#### Without throttling on /third endpoint:



<img src="https://user-images.githubusercontent.com/99293726/229205839-36da045a-67c3-45a5-b738-75c9a893dfb0.jpeg" width="300px" height="300px">



#### With throttling on /endpoint/first and /endpoint/second


<img src="https://user-images.githubusercontent.com/99293726/229206033-a5cad9b9-8401-4c68-b113-cc199bf1cfc8.jpeg" width="300px" height="300px">

