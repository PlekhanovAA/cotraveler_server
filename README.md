<h1>coTraveler server</h1>

The server part for the android application coTraveler that is located in this [repository](https://github.com/PlekhanovAA/cotraveler_android_client).
СoTraveler is an application for finding travel companions within a city or a certain geographical area. 
For more information about the application, check out the readme file for the Android client at the link above.

<h3>Preparation</h3>

1. Ensure the existence of files **docker-compose.yaml** and **Dockerfile** in root directory.
2. Make sure that the <em>/config</em> directory with monitoring settings exists.
3. Take the compiled code and package it in **coTraveler.jar** file.
4. Copy **coTraveler.jar** to <em>/target</em>.
5. Fix **docker-compose.yaml** like this <em>services:loki:command: --config.file=/etc/loki/loki-local-config.yaml</em>
6. Fix **/config/prometheus.yml** like this <em>scrape_configs:- job_name:- targets: ['my.remote.server.ip:port']</em> 
that is, substitute the server address or the name of the container (for example <em>scrape_configs:- job_name:- targets: ['cotraveler:8080']</em>).

<h3>Launch</h3>

```
docker-compose up -d
```