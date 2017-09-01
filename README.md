# Reservation Service

## Sample Requests

Create a new reservation:

```bash
http POST http://localhost:8080/reservations \
  firstName="Donald" \
  lastName="Trump" \
  roomNumber=1201  \
  startDate="2017-09-01" \
  endDate="2017-09-10"
```

List all reservations:

```bash
http GET http://localhost:8080/reservations
```
