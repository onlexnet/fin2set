Stateless service to convert bankcustome csv files to MT940 format

Features:
- recognize csv creator based on account number found inside the file
- TODO: not yet supported, but mBank and mBank CompanyNet can't be distinguished and some additional hint need to be provided

•Kodowanie polskich znaków - Konwerter domyślnie dopasowuje kodowanie polskich znaków do konkretnego banku. Zdarzają się jednak odstępstwa od przyjętych definicji. W takim wypadku należy wybrać kodowanie polskich znaków właściwe dla pliku będącego źródłem danych.

•Znak separatora pola danych - każdy z banków ma indywidualnie określony znak dzielący pole danych na odpowiednie informacje (dane adresowe, opis transakcji). Nieprawidłowo określony znak separatora skutkuje brakiem możliwości pobrania tych danych. To ustawienie jest pomocne gdy w pliku znak separatora jest inny niż domyślny dla banku.
### Interesting links
- [Open Source Java SWIFT Library](https://javadoc.io/doc/com.prowidesoftware/pw-swift-core/SRU2015-7.7/com/prowidesoftware/swift/model/mt/AbstractMT.html)
- [https://www.prowidesoftware.com/products/iso20022](https://javadoc.io/doc/com.prowidesoftware/pw-swift-core/SRU2015-7.7/com/prowidesoftware/swift/model/mt/AbstractMT.html)
- [iso 20022 message](https://www.iso20022.org/iso-20022-message-definitions?search=camt.053)
- [MT940 Format Overview](https://www.sepaforcorporates.com/swift-for-corporates/account-statement-mt940-file-format-overview/)
- [Opis formatu pliku wyciągów dziennych MT940](https://www.mbank.pl/pdf/msp-korporacje/bankowosc-elektroniczna/mbank-companynet-opis-formatu-pliku-eksportu-wyciagow-dziennych-mt940-v-1.3.pdf)
