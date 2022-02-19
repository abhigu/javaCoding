#What is it?
The goal of this coding project is to simulate the propagation of a virus, and the various factors that go into simulating such a process. This includes infection an death rates, incubation and healing, as well as random mutations that might change the simulation. 
Another goal of this project is to help condense my coding knowledge into a single piece, and to essentially show what I know and what I can do.  

#API

#How does it work?
<br>Node Class
<br>One singular Node represents a single person, and each Node has a specific set of values, such as if they are living and what variant they are infected with. The entire population is made of Node instances. Being infected, being healed, or checking if they are immune to a certain infection if they are infected are all at least partially done by this class. 
<br>Population Class
<br>The main thing that this class does is create the population for the epidemic to propagate in. It consists of 3 lists to store the 3 different kinds of people when considering the disease, which are the non infected, infected, and dead people. The population class is also responsible for moving recently infected, killed, or healed Nodes (people), after their variables and stats are changed during the Simulation. 
<br>Simulation Class
<br>Responsible for conducting the propagation. Consists of an overall while loop that runs, incrementing the day and running the newDay method, which heals, infects, and kills a certain amount of people because of the infection by using other methods present in the class. After their variables and stats are changed in this method, the Population class conducts the final moving of these Nodes into their new respective lists. 
<br>Variant Class
<br>Responsible for returning a virus when called. At the start of the Simulation, it is automatically called in the main method and used to run the simulation. When there is the need for a mutation, the Simulation class calls the mutation method in Variant. This method takes in the data of the previous Variant (the one that initially infected the individual), updates it, and returns it as the another Variant that has entered the Simulation. It is called a variable about of times, depending on how often the random chance produces the call for a mutation.    
  
  