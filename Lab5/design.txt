 Programmet kommer innehålla 4 huvud klasser

DrawControl:
- Klassen ärver Jframe eftersom huvud tråden kommer härstamma från den här klassen.
- Main kommer finns här där dens uppgift är att skapa en instans av DrawControl
- Konstruktorn kommer få konfigurera Jframe, initiera DrawView & DrawModel, koppla events till metoder i DrawModel  genom att använda lambdas eftersom ActionEvent är en ensam funktions klass.

DrawView:
- Klassen kommer innehålla kod för rendering av layout och element
- Den har även en metod för att rita alla former

DrawModel:
- Klassen innehåller det mesta av logiken där den hanterar all data
- Den har variabler för hålla koll på ritnings läge, färg och mus position vid interaktion.
- Klassen sparar även en historik av alla former för att kunna rita om när man ångrar.
- Hanterar även sparning och hämtning av bilder från disken.

Shape:
- Denna klass är för att hålla koll på en form där den vet sin egna dimension och vilken form den är. Klassen är gjort för att ha samma signatur över alla former exempelvis en ritfunktion.

