function sseStream(url) {
    console.log('Calling /' + url)
    if(typeof(EventSource) !== 'undefined') {
        console.log('Yes! Server-sent events supported!')
        let i = 0;
        let source = new EventSource('/' + url);
        source.onopen = function(event) {
            console.log('Opened!' + event)
        }
        source.onmessage = function(event) {
            if (i === 30) source.close();
            console.log('Got result! ' + event.data + '\t(' + i + ')')
            $('#res-field').html(event.data)
            i++;
        }
        source.onerror = function(event) {
            console.log('Error!' + event)
            source.close();
        }
    } else {
        console.log('Sorry! No server-sent events support..')
    }

//    console.log('sseStream')
//    $.ajax({
//        url: '/stream-sse-mvc',
//        type: 'GET',
//        accept: 'text/event-stream',
//        cacheControl: 'no-cache',
//        connection: 'keep-alive',
//        success: function (result) {
//            console.log('Got result! ' + result)
//            $('#res-field').html(result)
//        }
//    })
}

function update() {
    $.ajax({
        url: '/date',
        type: 'GET',
        contentType: 'application/json',
        success: function(result) {
            $('#res-field').html(result)
        }
    })
}