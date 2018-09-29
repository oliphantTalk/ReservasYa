const express = require('express');
const app = express();


app.get('/', function(req, res) {
    res.send('RESERVASYA');
});

/**
 * Métodos de respuesta
Los métodos en el objeto de respuesta (res) de la tabla siguiente pueden enviar una respuesta al cliente y terminar el ciclo de solicitud/respuestas. Si ninguno de estos métodos se invoca desde un manejador de rutas, la solicitud de cliente se dejará colgada.

Método	Descripción
res.download()	Solicita un archivo para descargarlo.
res.end()	Finaliza el proceso de respuesta.
res.json()	Envía una respuesta JSON.
res.jsonp()	Envía una respuesta JSON con soporte JSONP.
res.redirect()	Redirecciona una solicitud.
res.render()	Representa una plantilla de vista.
res.send()	Envía una respuesta de varios tipos.
res.sendFile	Envía un archivo como una secuencia de octetos.
res.sendStatus()	Establece el código de estado de la respuesta y envía su representación de serie como el cuerpo de respuesta.

 */

/**
  * //import renderEvaNetworkErrorMsg from './public/js/renderer.js'
const express = require('express');
const fetch = require('node-fetch');
const path = require('path');
// obtiene la ruta del directorio publico donde se encuentran los elementos estaticos (css, js).
const publicPath = path.resolve(__dirname, 'public');


var app = express();

// Para que los archivos estaticos queden disponibles.
app.use(express.static(publicPath));


/*

app.get('/', function(req, res) {
    res.sendFile(path.join(__dirname + '/public'));
});

let resultsHandler = function(req, res) {
    const adults = req.params.adults;
    const from = req.params.from;
    const to = req.params.to;
    const departureDate = req.params.departureDate;
    const returnDate = req.params.returnDate;
    console.log(adults, from, to, departureDate, returnDate);
    const url = `https://www.despegar.com.ar/shop/flights-busquets/api/v1/web/search?${adults}&children=0&infants=0&offset=0&limit=10&site=AR&channel=SITE&${from}&${to}&${departureDate}&${returnDate}&groupBy=default&orderBy=total_price_ascending&currency=ARS&viewMode=CLUSTER&language=es_AR&streaming=false&airlineSummary=false&user=95bf3781-cf84-48d7-bf37-81cf8478d776&sanity=VISIBLE&hash=463381299606902fa07ac827cf2563c9&_=1520001308987&clientType=web`;
    const headers = {
        "XDESP-SF-DEBUG": true,
    }
    const miInit = {
        method: 'GET',
        headers: headers,
        mode: 'cors',
        cache: 'default'
    };
    console.log(url);
    fetch(url, miInit)
        .then(response => response.json())
        .then(json => res.send(json))
        .catch(err => {
            console.error(err);
        });

}

app.get('/:from/:to/:departureDate/:returnDate?/:adults', resultsHandler);

app.listen(3200, function() {
    console.log('La aplicacion de vuelos corre en el puerto 3200');
});
  * 
  */