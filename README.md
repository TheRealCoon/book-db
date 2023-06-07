# Book-db

## Tasks

### Database setup
Create a PostgreSQL database named books and import the schema and the data from books.sql file.

-  A database named books exists.

- The book and author tables are created and filled with sample data.

### Database connection
Create a class named BookDbManager (in a subpackage named manager) with a connect() method that links to your books database and returns DataSource. Use PGSimpleDataSource to establish the connection.

- A BookDbManager class exists, and its connect() method connects to the database and returns a DataSource.

### DAOs
Create the AuthorDaoJdbc and BookDaoJdbc classes in the model subpackage. Each one of them must accept a DataSource as a constructor parameter. Instances of JDBC DAOs are managed by BookDbManager.

- The AuthorDaoJdbc and BookDaoJdbc classes are implemented.

- The AuthorDaoJdbc and BookDaoJdbc classes use PreparedStatements.

- Exceptions are chained and thrown as RuntimeExceptions, to avoid "exception swallowing".

### Console UI
Make this app functional with a simple console UI. Implement UserInterface methods, and call them from BookDbManager, using a main menu to submenu workflow. Make sure that the user can list and edit all records, as well as add new ones.

- The main menu with options a - Authors, b - Books and q - Quit navigates to the selected submenu and is functional.

- The resources menu with options l - List, a - Add, e - Edit and q - Quit is functional on the selected entities.