**Sign in**
----
Save a new member's data at DB

* **URL**

    >/members/join
 
 
* **Method**
    
    > POST
  

* **URL Params**
  
    > None
  
* **Data Params**
  * **Context-Type:** application/json
  
  ```json
  {
      "name" : "",
      "nickname" : "",
      "password" : "",
      "location" : {"city" : "", "gu" : ""}
  }
   ```
  
* **Success Response**
    * **Code:** 200 <br />
        **Content:** `{"id": Long, "error" : null}` <br />
        **Content:** `{"id": null, "error" : "fail"}`
      

* **Error Response**
  * **Code:** 409 CONFLICT <br />
  **Content:** `{ "error" : "fail"}`
    
<br>

**Log in**
----
Log in

* **URL**

  >/members/login


* **Method**

  > POST


* **URL Params**

  > None

* **Data Params**
  * **Context-Type:** application/json

  ```json
  {
      "nickname" : "",
      "password" : ""
  }
   ```

* **Success Response**
  * **Code:** 200 <br />
    **Content:** `{"id": Long, "error" : null}` <br />
    **Content:** `{"id": null, "error" : "wrong id or password"}`


* **Error Response**
  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ "error" : "wrong id or password"}`


<br>

**Duplicate check**
----
Check duplicated id

* **URL**

  >/members/duplicate-check/:nickname


* **Method**

  > GET


* **URL Params**

  > Required<br />
  >  * nickname=[String]

* **Data Params**
  
  > None

* **Success Response**
  * **Code:** 200 <br />
    **Content:** `"possible id"` <br />
    **Content:** `"duplicated id"`


* **Error Response**
  * **Code:** 404 NOT FOUND <br />


<br>

**Find Member**
----
Get specified Member's information 

* **URL**

  >/members/:id


* **Method**

  > GET


* **URL Params**

  > Required<br />
  >  * id=[Long]

* **Data Params**

  > None

* **Success Response**
  * **Code:** 200 <br />
    **Content:**
    ```json
    {
      "id" : Long,
      "name" : String,
      "nickname" : String,
      "location" : {"city": String, "gu": String},
      "level" : String,
      "testDate" :  date
    }
    ```


* **Error Response**
  * **Code:** 404 NOT FOUND <br />