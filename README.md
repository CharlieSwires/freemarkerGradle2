freemarker
----------
<p>in git bash
git clone https://github.com/scubetech-dev/freemarkerGradle</p>

<p>This contains both the java and node</p>

build
-----
<p>cd freemarkerGradle</p>
<p>gradle assemble</p>

<p>produces /build/libs/freemarkerGradle-0.0.1-SNAPSHOT.war </p>



deploy
------
<p>docker build --tag website:latest .</p>
<p>docker run -d -p 8888:8080 website:latest</p>


postman
-------
<p>http://localhost:8888/freemarker/TabularToPDF/columns/3</p>
{ "file" : "1,2,3"
}

<p>http://localhost:8888/freemarker/TabularToPDF/columns/3</p>
{ "file" : "hello,there,finally"
}

<p>Supports 1-5 columns can be extended simply.</p>
<p>http://localhost:8888/freemarker/GeneralToPDF</p>
{
    "inputHTML" : ".......",
    "replacementStrings" : "title,my title1\nnext,my next2\n"
}

<p>http://localhost:8888/freemarker/GeneralToPDF2</p>
{
"headerHTML": ".........",
"bodyFTL":".........",
"footerHTML": "...........",
"arrayOfItems": [{
"inputCSV": "one,1\ntwo,2\n",
"findingsText": [
{
"type": "Type1",
"note": "Findings...."
},
{
"type": "Type1",
"note": "Findings...."
}]
},
{
"inputCSV": "one,1\ntwo,2\n",
"findingsText": [
{
"type": "Type1",
"note": "Findings...."
},
{
"type": "Type1",
"note": "Findings...."
}]
}
]
}

<p>http://localhost:8888/freemarker/Init.</p>
{
    "inputFTL" : "<html><head><title>Charlie's Magic</title></head><body><#list systems as system><p>$system_index + 1}</p><p>${system.auditId},${system.objectId},${system.objectTitle},${system.objectType},${system.stage},${system.commChnl},${system.commLang},${system.plndStrtDt},${system.plndEndDt},${system.actlEndDt},${system.status},${system.mjrNc},${system.nextRevDt},${system.auditee},${system.auditeeName},${system.auditeeEmail},${system.auditeeLocations},${system.auditeeTimezone},${system.creBy},${system.creAt},${system.updBy},${system.updAt}</p></#list></body></html>",
"filename": "columnsPartha1Template.ftl"
}

<p>http://localhost:8888/freemarker/Partha1ToPDF.</p>
[
  {
    "auditId": 1,
    "objectId": "prj5698",
    "objectTitle": "Project 5698",
    "objectType": "PROJECT",
    "stage": "Stage-1",
    "commChnl": "WEB",
    "commLang": "FRENCH",
    "plndStrtDt": "2021-02-20T14:12:18.038Z",
    "plndEndDt": "2021-02-20T15:12:18.038Z",
    "actlEndDt": "2021-02-20T15:12:18.038Z",
    "status": "COMPLETED",
    "mjrNc": "1",
    "nextRevDt": "2021-02-20T15:12:18.038Z",
    "auditee": "d516d648-646d-4522-abaa-5da6a8f94b64",
    "auditeeName": "Roger Moore",
    "auditeeEmail": "rm@mail.com",
    "auditeeLocations": "UK",
    "auditeeTimezone": "GMT",
    "creBy": "be5b8aef-9218-4391-b74b-568e8146476b",
    "creAt": "2021-02-20T13:43:12.143Z",
    "updBy": "be5b8aef-9218-4391-b74b-568e8146476b",
    "updAt": "2021-02-20T14:12:18.821Z",
    "findingsText": [
      {
        "type": "Type1",
        "note": "Findings...."
      },
      {
        "type": "Type1",
        "note": "Findings...."
      }
    ]
  },
  {
    "auditId": 2,
    "objectId": "prj5698",
    "objectTitle": "Project 5698",
    "objectType": "PROJECT",
    "stage": "Stage-1",
    "commChnl": "WEB",
    "commLang": "FRENCH",
    "plndStrtDt": "2021-02-20T14:12:18.038Z",
    "plndEndDt": "2021-02-20T15:12:18.038Z",
    "actlEndDt": "2021-02-20T15:12:18.038Z",
    "status": "COMPLETED",
    "mjrNc": "1",
    "nextRevDt": "2021-02-20T15:12:18.038Z",
    "auditee": "d516d648-646d-4522-abaa-5da6a8f94b64",
    "auditeeName": "Roger Moore",
    "auditeeEmail": "rm@mail.com",
    "auditeeLocations": "UK",
    "auditeeTimezone": "GMT",
    "creBy": "be5b8aef-9218-4391-b74b-568e8146476b",
    "creAt": "2021-02-20T13:43:12.143Z",
    "updBy": "be5b8aef-9218-4391-b74b-568e8146476b",
    "updAt": "2021-02-20T14:12:18.821Z",
    "findingsText": [
      {
        "type": "Type1",
        "note": "Findings...."
      },
      {
        "type": "Type1",
        "note": "Findings...."
      }
    ]
  },
  {
    "auditId": 3,
    "objectId": "prj5698",
    "objectTitle": "Project 5698",
    "objectType": "PROJECT",
    "stage": "Stage-1",
    "commChnl": "WEB",
    "commLang": "FRENCH",
    "plndStrtDt": "2021-02-20T14:12:18.038Z",
    "plndEndDt": "2021-02-20T15:12:18.038Z",
    "actlEndDt": "2021-02-20T15:12:18.038Z",
    "status": "COMPLETED",
    "mjrNc": "1",
    "nextRevDt": "2021-02-20T15:12:18.038Z",
    "auditee": "d516d648-646d-4522-abaa-5da6a8f94b64",
    "auditeeName": "Roger Moore",
    "auditeeEmail": "rm@mail.com",
    "auditeeLocations": "UK",
    "auditeeTimezone": "GMT",
    "creBy": "be5b8aef-9218-4391-b74b-568e8146476b",
    "creAt": "2021-02-20T13:43:12.143Z",
    "updBy": "be5b8aef-9218-4391-b74b-568e8146476b",
    "updAt": "2021-02-20T14:12:18.821Z",
    "findingsText": [
      {
        "type": "Type1",
        "note": "Findings...."
      },
      {
        "type": "Type1",
        "note": "Findings...."
      }
    ]
  }
]

<p>http://localhost:8888/freemarker/isTamperedWith.</p>
{
    "fileB64": ".......",
    "sha1": "cGVjxRZmKwgwrCrhWDHYMSIfMvQ="
}

<p>http://localhost:8888/freemarker/ToPDF.</p>
{
    "inputHTML": ".........."
}

browser
-------
<p> If you have had a successful call to the PDF URL you can download the file here although the reply JSON contains the characters that make up the test file so all you have to do is convert them to bytes from Base64 string to then a file.</p>
<p>http://localhost:8888/freemarker/downloadFile</p>

