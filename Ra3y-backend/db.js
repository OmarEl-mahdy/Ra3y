// Importing the mysql library:
//
// Similar to import in Java, however, you can name the library object whatever
// you want on the left hand side.
// I recommend you `const` all your imports.
const mysql = require("mysql");

 let connection = mysql.createConnection({
     host: "localhost",
     user: "root",
     password: "password",
     database: "Ra3yDB"
 });


//let connection = mysql.createConnection({
//    host: "www.db4free.net",
//    user: "mashraf",
//    password: "ashraf1234",
//    database: "ra3ydb"
//});

connection.connect(function (err) {
    if (err) {
        console.error("Failed to connect to database- throwing error:");
        throw err;
    }
    console.log("Connected to database succesfully.");
});

// Each Javascript file has an optional export. This export can be anything: here I made the connection object the export.
module.exports = connection;
