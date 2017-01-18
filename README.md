#===================================
# Diskuterade funderingar:
Allt är flyttat till rätt avsnitt :)

#====================================
# Odiskuterade funderingar:
Vi vill antagligen ha flera olika typer av containers och inte bara backpacks. Hur ska detta programmeras?
Jag tänker mig föremål som en minecraft kista. Av flera anledningar verkar det lämpligt att containers är items. 
Ett vettigt sätt att göra det på är att ha en abstrakt klass container som ärver av items. 
Backpack skulle sedan kunna ärva från container. 

Jag vill att vi ska vara konsekventa med koordinater.
Vill vi att (0, 0) ska vara längst upp till vänster (matriser) eller längst ner till vänster (koordinater)?

#====================================
# Att göra:
Items - En abstrakt klass "Item". Den bör till en början ha metoder som getId(), getMaxStackSize(). 
Det är nog lämpligt att varje stack bara är en instans av klassen. I så fall är även en getCurrentStackSize() lämplig. 

Backpack - Vi pratade om backpack. Efter att ha tänkt lite tror jag att det är lämpligare att kalla klassen container. 
Den kan ha de egenskaper vi bestämde att backpack skulle ha när vi diskuterade. 

#====================================
# Aktuella ideér:
Items - Alla möjliga typer av items. Alla dessa bör ha ett unikt ID. I framtiden kan det finnas flera
varianter av samma item med olika kvalité och durability.  

Backpack - Man bör ha en backpack att lägga föremål i. Till en början så tar alla items
lika mycket plats i backpacken och det finns inget viktsystem. En del föremål stackar,
detta bör tänkas på vid implementering av backpack. Se även min kommentar under "Odiskuterade funderingar". 

Interaktioner - Man kanske kan fråga en tile vad som går att göra på den. Sedan är det karaktären som försöker interrakta med den. 
Kanske en klass för interaktions klass som håller koll på vad man kan göra på en viss tile. 

#====================================
# Ideér till längre fram: 
Equipments - Det bör finnas flera olika sorters equipments, t.ex. byxor, tröja, hatt, skor. 

Tools - Man behöver olika typer av tools för att göra olika saker, till exempel pickaxe för mining, axe för woodcutting,
spade för att gräva, vapen-liknande för att slåss, osv. 

Items - Kvalité och durability till items.