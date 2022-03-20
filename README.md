### Run Jar
* Local: mvn spring-boot:run

### Compile Code
* mvn clean compile -e

### Test Code
* mvn clean test -e

### Jar Code
* mvn clean package -e

### Testing Application
* Test DXC: http://localhost:8081/rest/msdxc/dxc?sueldo=4000&ahorro=5000
* Test Impuesto: http://localhost:8081/rest/msdxc/impuesto?sueldo=4000&ahorro=5000
* Test Saldo: http://localhost:8081/rest/msdxc/saldo?sueldo=4000&ahorro=5000
