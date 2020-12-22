# GameOfLife
 
Projekt wykonany w ramach zajęć z Programowania Obiektowego

## Wymagania projektowe:

 1) Program ma wyświetlać animację pokazującą pozycję zwierząt, ich energię w dowolnej formie (np. koloru) oraz pozycje roślin.
 
 2) Można użyć dowolnego sposobu wyświetlania animacji (również tekstowego), ale animacja nie może polegać na wyświetlaniu po sobie łańcuchów tekstu (można natomiast wyświetlać łańcuchy tekstu w tym samym miejscu, wymaga to jednak użycia odpowiedniej biblioteki).
 
 3) Program musi umożliwiać zatrzymywanie oraz wznawianie animacji w dowolnym momencie (niezależnie dla każdej mapy - patrz niżej).
 
 4) Program ma pozwalać na śledzenie następujących statystyk dla aktualnej sytuacji w symulacji:
liczby wszystkich zwierząt,
liczby wszystkich roślin,
dominujących genotypów,
średniego poziomu energii dla żyjących zwierząt,
średniej długości życia zwierząt dla martwych zwierząt,
średniej liczby dzieci dla żyjących zwierząt.

 5) Po zatrzymaniu programu można:
wskazać pojedyncze zwierzę, co powoduje wyświetlenie jego genomu,
wskazać pojedyncze zwierzę, w celu śledzenia jego historii:
określenia liczby wszystkich dzieci, po n-epokach,
określenia liczby wszystkich potomków, po n-epokach,
określenia epoki, w której zmarło,
wskazać wszystkie zwierzęta z dominującym genomem.

 6) Program ma umożliwić wyświetlenie symulacji jednocześnie na dwóch mapach, z identycznymi parametrami początkowymi, lecz niezależnie losowanymi decyzjami.
 7) Program powinien umożliwiać uzyskanie statystyki (jak w punkcie 4) po określonej liczbie epok w formie pliku tekstowego. Statystyki powinny stanowić uśrednienie wartości z poszczególnych epok.
 Źródło: https://github.com/apohllo/obiektowe-lab/tree/master/lab8
 
 ## Działanie:
 
 W klasie World rozpoczynamy działanie programu tworząc nowe okno startowe. Urzywając przycisku "Start" uruchamiamy symulacje wczytując parametry ustawione w pliku "parameters.json" o budowie:
 {
	"width": szerokość planszy,
	"height": wyskokość planszy,
	"startEnergy": początkowa energia zwierzęcia,
	"moveEnergy": energia potrzebna do ruchu zwierzęcia,
	"plantEnergy": energia pozyskiwana z roślin,
	"jungleRatio": współczynnik określający wielkość jungli,
	"noOfAnimals": startowa liczba zwierząt,
	"noOfPlants": startowa liczba roślin,
	"max_iteration": maksymalna ilość epok
}
Okno startowe nie znika to otwarciu okna symulacji, dzięki temu naciskając przycisk "Start" ponownie możemy urtuchomić symulacje wielokrotnie (na każdej z nich decyzje będą podejmowane niezależnie) pozwala to wykonać założenie nr 6.

Symulacja wyświetla się w oknie symulacji wraz z wymaganymi statystkami.
Oznaczenie elementów:
zielony kwadrat - trawa
zielony okrąg - zwierzę o dużej ilości energii (>70)
czarny okrąg - zwierzę o średniej ilosći energii (>40)
czerowny okrąg - zwierzę o małej ilości energii (<=40)

W panelu statystyk dostępne są trzy przyciski : "Start", "Stop", "Save"
"Start" - uruchamia zatrzymaną symulacje
"Stop" - zatrzymuje symulacje
"Save" - zapisuje uśrednione statystki symulacji to pliku "report.txt"
