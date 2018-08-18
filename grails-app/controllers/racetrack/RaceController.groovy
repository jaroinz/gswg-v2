package racetrack

import org.compass.core.engine.SearchEngineQueryParseException

class RaceController {

    def scaffold = Race

    def search = {
        flash.message = "Search results for: ${params.q}"
        def resultsMap = Race.search(params.q, params)
        render(view:'list',
                model:[
                        raceInstanceList:resultsMap.results,
                        raceInstanceTotal:Race.countHits(params.q)
                ]
        )
    }

    def list = {
//        render(view:'list',
//                model:[:]
//        )
    }

}
