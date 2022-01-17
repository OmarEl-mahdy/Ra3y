    FLUSH PRIVILEGES;
    ALTER USER "root"@"localhost" IDENTIFIED BY "password";
    Create database Ra3yDB;
    Use Ra3yDB;

    Create table owner(
       UID VARCHAR(255) PRIMARY KEY,
       fname VARCHAR(255),
       lname VARCHAR(255),
       email VARCHAR(255),
       pass VARCHAR(255),
       phonenumber VARCHAR(255)
    );


    CREATE TABLE sitter(
       UID VARCHAR(255) PRIMARY KEY,
       fname VARCHAR(255),
       lname VARCHAR(255),
       email VARCHAR(255),
       pass VARCHAR(255),
       phonenumber VARCHAR(255),
       priceperhour FLOAT,
       LocationID INT,
       FOREIGN KEY(LocationID) REFERENCES location(ID)
       ON UPDATE CASCADE ON DELETE CASCADE
    );


    CREATE TABLE IF NOT EXISTS ownersitteragree(
        sitteruid VARCHAR(255),
        owneruid VARCHAR(255),
        timestamp TIMESTAMP, 
        emergencyContact VARCHAR(255),
        rating INT,
        review VARCHAR(255),

        FOREIGN KEY(sitteruid) REFERENCES sitter(UID)
        ON  UPDATE CASCADE ON DELETE CASCADE,
        FOREIGN KEY(owneruid) REFERENCES owner(UID)
        ON  UPDATE CASCADE ON DELETE CASCADE,
        PRIMARY KEY (sitteruid,owneruid, timestamp)
    
    );

    CREATE TABLE IF NOT EXISTS ownersitterrequest(
        sitteruid VARCHAR(255),
        owneruid VARCHAR(255),
        timestamp TIMESTAMP, 
        FOREIGN KEY(sitteruid) REFERENCES sitter(UID)
        ON  UPDATE CASCADE ON DELETE CASCADE,
        FOREIGN KEY(owneruid) REFERENCES owner(UID)
        ON  UPDATE CASCADE ON DELETE CASCADE,
        PRIMARY KEY (sitteruid,owneruid, timestamp)
    
    );


    CREATE TABLE IF NOT EXISTS location(
        ID  INT AUTO_INCREMENT PRIMARY KEY,
        latitudes DECIMAL(8,6),
        longitudes DECIMAL(9,6)
    );



    CREATE TABLE IF NOT EXISTS vaccinations(
        vaccineName VARCHAR(255) PRIMARY KEY,
        animaltype VARCHAR(255)
    );


    CREATE TABLE IF NOT EXISTS services(
        name VARCHAR(255) PRIMARY KEY,
        phonenumber VARCHAR(255),
        address VARCHAR(255)
    );
