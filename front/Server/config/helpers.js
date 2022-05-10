const MySqli = require('mysqli');

// one config
let conn = new MySqli({
  host: 'localhost', // IP/domain  
  post: 3306, //port, default 3306  
  user: 'mega_user', // username
  passwd: 'megauser123', // password
  db: 'mega_shop' // the default database name  【optional】
});

let db = conn.emit(false, '');

module.exports = {
    database: db
};