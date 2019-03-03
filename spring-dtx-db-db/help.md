

```bash
curl -X POST -d '{"customerId":1, "title": "new title", "amount": 100}' -H 'Content-Type: application/json' http://localhost:8080/api/customer/order
curl -X POST -d '{"customerId":1, "title": "new title error1", "amount": 100}' -H 'Content-Type: application/json' http://localhost:8080/api/customer/order
curl -X POST -d '{"customerId":1, "title": "new title error2", "amount": 100}' -H 'Content-Type: application/json' http://localhost:8080/api/customer/order

```