# simple script to run locally SQL server to allow start application using 'dev' profile
docker run -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=mypassword1!" -p 1433:1433 mcr.microsoft.com/mssql/server:2019-CU14-ubuntu-20.04
