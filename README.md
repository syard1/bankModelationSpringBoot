#Startimi dhe menyra e punes se Aplikacionit

Ketu eshte implementimi i nje sistemi bankar ne Spring boot.

Brenda folderit src ndodhet java file DemoApplication qe permban metoden main nga edhe nise ekzekutimi i fileve.

Aplikacioni i dhene eshte nje web aplikacion, keshtu qe me startimin e tij porta 9000 do te sherbej si porte komunikuese me serverin ne te cilin po ekzekutohet aplikacioni. Ne rast se kete port e keni te zene ne kompjuterin tuaj atehere kete mund te ndrroni ne filen application.properties(server.port=9000) qe ndodhet brenda shtegut src-main.

Per nga ndertimi, softuesri i dhene eshte i ndare ne dy pjese te medha logjike: Pjesa kontrollerit dhe pjesa e modelit.

Ne pjesen e kontrollerit eshte e shkruar logjika e nevojshme ashtu qe te pranoj dhe procesoj per tek modeli kerkesat te cilat vijne nga pjesa e frontendit.
Per te derguar kerkesa drejt keti softueri ju mund te shikoni endpointat te cilet jane te shkruar ne paketen Controller,pastaj per te derguar kerekesat mund te perdorni simulues te ndryshem. Per te pasur me te lehte testimin e endponiteve kam integruar ne aplikacion swagger, ku mund te shihni te gjitha specifikimet. Per te perdorur kete nese jeni ne porten 9000 atehere mund ta kopjoni kete url: http://localhost:9000/swagger-ui/index.html#/bank-controller/transferAmount.
Ne swagger endpointat te cilet shfaqen jane:
POST /bank/createbank --na mundeson te krijome nje banke psh:
{
           "bankName":"Procredit",
           "transactionFlat": 2,
           "transactionPercentage":0.5,
           "feeInUse":"percentageFlat"
}

PUT /bank/changeFeeInUse/{fee}/{bankName} -- mundeson bankes te ndryshoj menyren e tarifimit fee duhet te jete flatFee ose percentageFee.

Kurse endpointat tjere jane tejet pershkrues.

Pjesa e modelit eshte e ndare ne tri pjese pjesa Service qe permbane logjiken e softuesrit, pjesa e modelit qe modelon entitetet , per shaka se per komunikim me databaze eshte perdorur Spring data JPA qe mundeson ruajtjen e entiteteve ne databaze si pasqyrim te ketyre modeleve, si dhe eshte pjesa e "Dao" e cila permbane interfece te cilat mundesojne komunikimin me databaze.

Sa i perktet databazes eshte perdorur in-memory databse. konfigurimet e nevojshme mund ti shihni ne application.properties file, gjithashtu mund te ju qaseni permes url: http://localhost:9000/h2.




