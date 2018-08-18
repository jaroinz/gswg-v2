package racetrack

class AdminFilters {

    def filters = {
        adminOnly(controller: '*',
                action: "(create|edit|update|delete|save)") {
            before = {
                if (!session?.user?.admin) {
                    flash.message = "Sorry, admin only"
                    redirect(controller:"race", action:"index")
                    return false
                }
            }
        }
//        all(controller:'*', action:'*') {
//            before = {
//
//            }
//            after = { Map model ->
//
//            }
//            afterView = { Exception e ->
//
//
//       }
    }
}
