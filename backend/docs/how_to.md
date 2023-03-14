For Development with minikube Kubernetes

To Access the ip of backend:
`minikube service backend-service --url`   

To Spin a postgres db with exposed IP
`kubectl apply -f kubernetes/db-exposed.yaml`

To Delete the old services
`kubect delete -f file.yaml`

To SSH into kubernetes pods
`kubectl exec --stdin --tty pod_name -- /bin/bash` 