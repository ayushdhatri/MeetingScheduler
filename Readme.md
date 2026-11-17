List down the functional and non-functional requirements:

#####################################################
First understand what is the flow of meeting scheduler

<--------------How to think about functional requirements---------->
First lets list down all the actors who are going to use our platform
For each actor decide the happy path
For each actor decide the unhappy path
From the above two points, list down the functional requirements

#############FunctionalRequirements###################

1) User should be able to schedule a meeting, add list of participants, meeting timings along with organizer
2) System should notify the participants about the booking
3) System should show the user list of meeting before booking.
4) User should be able to cancel the meeting, which should be notified to the participants
5) System should filter the rooms based on meeting constraints such as capacity, duration, roomTyppes
6) System should automatically detect and reject conflict meetings
7) System should support configurable room selection strategies(first available, best fit by capacity)



############## NonFunctionalRequirements ######################
1) The design should be modular, maintainable
2) System should handle concurrent booking request without race condition

