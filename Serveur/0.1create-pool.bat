asadmin create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGSimpleDataSource --restype javax.sql.ConnectionPoolDataSource --property portNumber=5432:password=bookshelf:user=bookshelf:serverName=localhost:databaseName=biblio biblioPool
asadmin create-jdbc-resource --connectionpoolid biblioPool jdbc/biblio
pause