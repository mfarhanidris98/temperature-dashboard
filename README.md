
# Temperature Dashboard

A web application that visualizes temperature of uploaded csv file.


## Installation

Setup mailer information at **application.properties** 
in the resources directory.

```bash
...
# quarkus mailer
# Your email address you send from - must match the "from" address from sendgrid.
quarkus.mailer.from=[insert mailer gmail here]
# The SMTP host
quarkus.mailer.auth-methods=DIGEST-MD5 CRAM-SHA256 CRAM-SHA1 CRAM-MD5 PLAIN LOGIN
quarkus.mailer.host=smtp.gmail.com
# The SMTP port
quarkus.mailer.port=587
# If the SMTP connection requires SSL/TLS
quarkus.mailer.start-tls=REQUIRED
# Your username
quarkus.mailer.username=[insert mailer gmail here]
# Your password
quarkus.mailer.password=[insert gmail App password here]
...

```

Generate PrivateKey in resources file.
```
openssl genrsa -out src/main/resources/rsaPrivateKey.pem 2048 -ii
```
## Run Locally

Clone the project

```bash
git clone https://github.com/mfarhanidris/temperature-dashboard.git
```

Start the backend project and run 
```
cd backend
mvn quarkus:dev
```

Start the web app

```bash
cd frontend
npm start
```


## Technologies

**Client:** ReactJs

**Server:** Quarkus

**Database:** PostgreSQL 

## Features

- Registration with sent email
- Login and logout
- Drag-and-drop CSV file
- Table view from CSV
- Graph preview

### Future Work

- Graph reflects dropped file.
- Model Inferencing


