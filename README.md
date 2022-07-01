
# Progetto di Ingegneria del Software (A.A. 2021-2022)

:it: Lo sviluppo di questo progetto è parte del corso di ingegneria del software del Politecnico di Milano, e in qualità di prova finale è necessario per il conseguimento della laurea triennale in ingegneria informatica. Il progetto è svolto in gruppo, e lo scopo è quello di implementare, seguendo le regole dell'ingegneria del software, un gioco da tavolo assegnato.


:uk: The development of this project is part of the software engineering course at the Polytechnic University of Milan, and as a final examination, it is necessary for the bachelor's degree in engineering of computing systems. It is a group project, and the goal is to implement an assigned board game, following the software engineering principles.


![enter image description here](https://img.dungeondice.it/49229-large_default/eriantys.jpg)



## Componenti del gruppo
- ###   Endi Kucuku ([@endikucuku](https://github.com/endikucuku))
- ###   Alen Kaja ([@alenkaja00](https://github.com/alenkaja00))
- ###   Giovan Battista Landolina ([@giovanbattista01](https://github.com/giovanbattista01))
<br/>


## Funzionalità implementate
| Functionality                  | Status |
|:-------------------------------|:------:|
| Regole Semplificate            |  [✅]   |
| Regole Complete                |  [✅]   |
| Socket                         |  [✅]   |
| CLI                            |  [✅]   |
| GUI                            |  [✅]   |
|                                |        |
| 12 Carte Personaggio           |  [✅]   |
| Partite Multiple               |  [✅]   |
| Resilienza alle disconnessioni |  [✅]   |



## Casi di Test

**Criterio di Copertura: righe di codice.**




| Package |Classe Testata | Copertura |
|:-----------------------|:------------------|:------------------------------------:|
| Model | cards |  91/91 (100%)
| Model | components | 162/162 (100%)
| Model | gameClasses | 318/359 (88%)
| Model | Global Package | 667/710 (93%)

## Comandi per il corretto utilizzo

**Come fare partire il jar:**
CLI : java -jar cliEriantys.jar
GUI : java -jar guiEriantys.jar

**Note per la corretta visualizzazione della CLI :**

- Controllare che il font "candara light" sia correttamente installato sul computer
- regolare lo zoom in modo appropriato

