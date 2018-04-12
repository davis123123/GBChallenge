# GBChallenge

Problem Description:

1.Retrieve and print out the data received from the url above.

2.Parse the data retrieved from the server into a list of Java objects

3.Display your objects in a RecyclerView
	Should display the name, city, state, and end date

4.In addition the object’s name, have your view display the image located at each object’s icon url.

I used Retrofit and GSON to pull the data and parse the JSON objects.
I then created 3 types of objects that store parsed data, UpcomingGuide, Data (an array of Data(s) nested in Upcoming Guide), and Venue (object nested in Data which contains city and state)
With all the objects held in place, I placed the items which need to be displayed on an adapter. The adapter then calls the icon url to download (using picasso) and display each image. I put the downloading method in the bindholder because this will allow the rest of the recyclerview to render without waiting on the images
(which usually takes longer).

I did not spend much time on the UX due to the time constraint but the display is overall readable and organized.
