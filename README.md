# KalkulatorHipoteczny

Aplikacja stworzona do obliczania kredytu hipotecznego

Warianty kredytów hipotecznych jakie możemy obliczać:

Kredyt o stałych ratach, w tym przypadku cześć kapitałowa i odsetkowa daje nam stałą wartość

Kredyt o stałych ratach kapitałowych w tym przypadku każda następna rata którą spłacamy jest mniejsza od poprzedniej

W Aplikacji jest uwzględniona też możliwość wcześniejszej nadpłady kredytu, w przypadku ,gdy będziemy dokonać dodakowych
nadpład kredytu, mamy do wyboru dwie opcje : 

Opja redukująca czas trwania
Nasz kredyt zostanię skórcony o poczczęgólną ilość miesięcy w zależność o wielkości dokonanej nadpłąty

Opcja reduująca wyokość pozostałych rat
W tym przydaku ,gdy dokonamy nadpłady czas trwania kredytu zostanie cały czas taki sam, ale wysokości
pozostałych rat zostaną pomniejszone.

Dane wejsciowe jakie wprowadzamy W klasie InputData są następujące: 
1. Moment rozpoczecia kredytu 
2. Kwota kredytu
3. Wysokość oprocentowania wibor 
4. Rodzaj rat w jakich kredyt jest spłanacy
5. Czas trwania kredytu 
6. Wibor
7. Oprocentowanie kredytu narzucone przez bank
8. (Opcjonalnie) Nadpłaty jakie będziemy dokonywać w trakcie trwania kredytu należy podać
(miesiąc i kwotę jaką chcemy nadpłącić) oraz również sposób w jaki chcemy aby nasz kredyt został nadpłacony
czy ma zostać obniżony czas trwania kredytu czy pozostałe raty którę będzie spłacać mają być niższe
9. (Opcjonalnie) Oprocentowania jakie bank pobiera za dodatkowe wcześniejsze nadpłaty

Aplikacja oblicza nam sume całkowitych odsetek jaką zapłaciliśmy za kredyt 
Dodatkową prowizję jaką zapłaciliśmy z tytułu dokonywania wcześniejszych nadpłat, jeśli taka istnieje
Sume strat jaką ponieśliśmy biorąc kredyt

Podczas budowy poszczególnych rat kredytu wyświetlane w terminalu jest 
NR raty | Data w której bedziemt płacić daną ratę | rok spłaty | miesiąc spłaty | Rata | Odsetki | Nadpłata | Posostała Kwota | Pozostałą ilosć rat|

przkkład: 
NR: 1 DATA 2020-01-06 ROK: 1 MIESIĄC: 1 RATA: 2763.95 ODSETKI: 901.45 KAPITAL: 1862.50 NADPLATA: 0.00 POZOSTALO KWOTA: 296137.50 POZOSTALO MIESIECY: 159
