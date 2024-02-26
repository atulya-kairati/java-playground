Build docker image
```sh
docker build . -t 4ager/react-frontend 
```

```
docker image ls
docker push username/image-name

docker run -d --name react-frontend -p 3000:5173 4ager/react-frontend
```