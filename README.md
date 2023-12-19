# Bu projede yerel Docker PostgreSQL veritabanı kullanılır. 
Veritabanını kullanmak için makine üzerinde Docker'ın yüklü olması gerekir.<br><br> 
Veritabanını oluşturup çalıştırmak ve projeyi çalıştırmak için, terminalden docker-compose.yml dosyasının bulunduğu dizine giderek aşağıdaki komutu çalıştırınız.

```json
docker compose up -d
```

# API Dökümantasyonu

## AuthController
### Register

- Request URL: `/api/auth/register`
- Request Method: `POST`
- Request Body:

```json
{
    "name" : "alperen",
    "surname" : "gümrükçü",
    "email" : "alperen.gumrukcu@gmail.com",
    "password" : "123456",
    "phoneNumber": "05533089796"
}
```

- Response : 
```json
{
    "message": null,
    "data": null,
    "success": true
}
```

### Login

- Request URL: `/api/auth/login`
- Request Method: `POST`
- Request Body:

```json
{
    "email" : "alperen.gumrukcu@gmail.com",
    "password" : "123456"
}
```

- Response :
```json
{
    "message": null,
    "data": {
        "user": {
            "id": 1,
            "name": "alperen",
            "surname": "gümrükçü",
            "email": "alperen.gumrukcu@gmail.com",
            "phoneNumber": "05533089796"
        },
        "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiZXhwIjoxNzA1NjA4NjM3fQ.DT5OZutpZBU1WUWwGq_7RSLBwshiggUY6oAHtc2T7O8"
    },
    "success": true
}
```


# CustomerController

### Create Customer

- Request URL: `/api/customer/save`
- Request Method: `POST`
- Request Body:

```json
{
    "name" : "api",
    "surname" : "apiSurname",
    "email" : "postman@email.com",
    "address" : "postman address"
}
```

- Response :
```json
{
    "message": "Müşteri başarıyla kaydedildi.",
    "data": {
        "id": 1,
        "name": "api",
        "surname": "apiSurname",
        "email": "postman@email.com",
        "address": "postman address",
        "createdAt": "2023-12-19T20:11:26.568189"
    },
    "success": true
}
```

### Update Customer

- Request URL: `/api/customer/update`
- Request Method: `POST`
- Request Body:

```json
{
  "id" : 1,
  "name" : "guncellenmisAd",
  "surname" : "guncellemisSoyad",
  "email" : "guncellenmiseposta@gmail.com",
  "address" : "Güncellenmiş Adres"
}
```

- Response :
```json
{
    "message": "Müşteri güncellendi.",
    "data": {
        "id": 1,
        "name": "guncellenmisAd",
        "surname": "guncellemisSoyad",
        "email": "guncellenmiseposta@gmail.com",
        "address": "Güncellenmiş Adres",
        "createdAt": "2023-12-19T20:11:26.568189"
    },
    "success": true
}
```
### ReadAll Customer

- Request URL: `/api/customer/all`
- Request Method: `GET`
- Response :
```json
{
    "message": null,
    "data": [
        {
            "id": 1,
            "name": "guncellenmisAd",
            "surname": "guncellemisSoyad",
            "email": "guncellenmiseposta@gmail.com",
            "address": "Güncellenmiş Adres",
            "createdAt": "2023-12-19T20:11:26.568189"
        }
    ],
    "success": true
}
```

### Delete Customer

- Request URL: `/api/customer/delete`
- Request Method: `DELETE`
- Request Param: `id=[Long]`
- Response :
```json
{
    "message": null,
    "data": null,
    "success": true
}
```

## FileController

### Create File

- Request URL: `/api/file/upload`
- Request Method: `POST`
- Request Body(Form-Data):

```yaml
{
  Key: file, Value: image.jpeg
  Key: customerId, Value: 2
}
```

- Response :
```json
{
    "message": "Kayıt Başarılı",
    "data": {
        "id": 2,
        "name": "image.jpeg",
        "path": "upload/image.jpeg",
        "createdTime": "2023-12-19T23:29:57.614162",
        "customerId": 2,
        "resource": null,
        "contentType": "image/jpeg"
    },
    "success": true
}
```

### Update File

- Request URL: `/api/file/update`
- Request Method: `POST`
- Request Body(Form-Data):

```yaml
{
  Key: file, Value: example.jpeg
  Key: fileId, Value: 2
}
```

- Response :
```json
{
    "message": null,
    "data": {
        "id": 2,
        "name": "example.jpeg",
        "path": "upload/example.jpeg",
        "createdTime": "2023-12-19T23:29:57.614162",
        "customerId": 2,
        "resource": null,
        "contentType": "image/jpeg"
    },
    "success": true
}
```

### Get File

- Request URL: `/api/file/{{id}`
- Request Method: `GET`
- Path Varable: `id=[Long]`


- Response :

  - Dosya otomatik olarak inecektir.


### Delete File

- Request URL: `/api/file/delete/{id}`
- Request Method: `DELETE`
- Path Varable: `id=[Long]`
  
- Response :
```json
{
    "message": null,
    "data": null,
    "success": true
}
```

### List Files

- Request URL: `/api/file/all`
- Request Method: `GET`

- Response :
```json
{
    "message": "Tüm dosyaların listesi",
    "data": [
        {
            "id": 3,
            "name": "IMG_0096.jpeg",
            "path": "upload/IMG_0096.jpeg",
            "createdTime": "2023-12-19T23:43:13.379532",
            "customerId": 2,
            "resource": null,
            "contentType": "image/jpeg"
        },
        {
            "id": 4,
            "name": "IMG_0096.jpeg",
            "path": "upload/IMG_0096.jpeg",
            "createdTime": "2023-12-19T23:43:32.033967",
            "customerId": 2,
            "resource": null,
            "contentType": "image/jpeg"
        }
    ],
    "success": true
}
```

### List File By CustomerId

- Request URL: `/api/file/customer_id`
- Request Method: `GET`
- Request Param: `customerId=Long`

- Response :
```json
  {
    "message": "Tüm dosyaların listesi",
    "data": [
        {
            "id": 3,
            "name": "IMG_0096.jpeg",
            "path": "upload/IMG_0096.jpeg",
            "createdTime": "2023-12-19T23:43:13.379532",
            "customerId": 2,
            "resource": null,
            "contentType": "image/jpeg"
        },
        {
            "id": 4,
            "name": "IMG_0096.jpeg",
            "path": "upload/IMG_0096.jpeg",
            "createdTime": "2023-12-19T23:43:32.033967",
            "customerId": 2,
            "resource": null,
            "contentType": "image/jpeg"
        }
    ],
    "success": true
}
```


### /auth/login ve /auth/register hariç tüm endpointler authentication istemektedir.
