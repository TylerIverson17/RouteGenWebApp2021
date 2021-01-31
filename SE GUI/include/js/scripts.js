// IIFE = immediately invoked function expression
(function($){
    let currentLocation = null // [1, 3]
    const destinations = []
    const $panelElement = $('#panel')
    const $panelMessage = $('#message', $panelElement)
    const $selectedHouses = $('#selected-houses', $panelElement)
    const $panelActions = $('#panel-actions')

    function updateInfoPanel(){
        if(!currentLocation){
            $panelMessage.html('<h3>Select your current location</h3>')
        } else {
            $panelMessage.html(`<h3>You are at street #${currentLocation[0]}, home #${currentLocation[1]} \n<h4>Select your next destination</h4>`)
            $('button#done', $panelActions).show()

            if(destinations.length){
                $('ol', $selectedHouses).html('')
                destinations.forEach((destination) => { // [4, 5]
                    const deleteButton = $('<span class="remove">x<span>')

                    deleteButton.click(handleDeleteItem)

                    const listItem = $(`<li>Street #${destination[0]} and home #${destination[1]}</li>`)
                    listItem.append(deleteButton)
                    listItem.appendTo('ol', $selectedHouses)
                })

                $('button#done', $panelActions).removeAttr('disabled')
            } else {
                $('ol', $selectedHouses).html('')
                $('button#done', $panelActions).attr('disabled', true)
            }
        }
    }

    function handleDeleteItem(event){
        const deleteElement = $(event.currentTarget)
        const parentListItem = deleteElement.parent()
        const olElement = parentListItem.parent()

        const itemIndex = $('li', olElement).index(parentListItem)
        destinations.splice(itemIndex, 1)
        
        updateInfoPanel()
    }

    function getHomePosition(element /* html element */){
        // DOM = (Document Object Model) manipulation
        const homeElement = $(element)

        const streetElement = homeElement.parent()
        const streetId = streetElement.data('streetid')

        const homeId = $('.home', streetElement).index(homeElement)

        return [streetId, homeId]
    }

    function handleHomeClick(event){
        const homePosition = getHomePosition(event.currentTarget)
        if(!currentLocation){
            currentLocation = homePosition
        } else {
            destinations.push(homePosition)
        }
        
        updateInfoPanel()
    }

    async function getPathByDestinations(){
        /**
         * RESTful API endpoint => exposed by server
         * accepts: 
         *  destinations list:
         *      2d array: 
         *      [
         *          [3, 5], <~~ [street_number, home_number]
         *          [4, 3], 
         *          [0, 1], 
         *          [8, 10],
         *      ]
         *  current location: [street_number, home_number]
         *  
         * returns: travel path
         */
        const response = await fetch('http://localhost:8080/getPathToDestinations', {
            body: {
                currentLocation,
                destinations 
            }
        }) 

        updateInfoPanel()
    }

    // App starts
    updateInfoPanel()

    // JavaScript way
    // document.querySelectorAll('.home').addEventListener('click', getHomePosition)

    /**
     * when you call a function you should end it with opening and closing pranthesis
     * getHomePosition()
     */

     
    // jQuery way
    /**
     * When you pass a function as an event handler
     * you MUST not add pranthesis
     */
    $('.home').click(handleHomeClick)
    $('button#done').click(getPathByDestinations)

})(jQuery)