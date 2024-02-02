# BuildWeekJava

1. Introduzione
   * Progetto: Sistema di Gestione Trasporti Pubblici.
   * Team: Chiara, Kavidu, Luca.
   * Obiettivo: Implementare un sistema completo per gestire biglietti, abbonamenti e parco mezzi per trasporti pubblici.
----------------------------------------------------------------------------------------------------------------
2. Fase di Progettazione
   * Per il diagramma principale è stato utilizzato drawsql.app.
   * https://drawsql.app/teams/croco-1/diagrams/team-8-bw
   * Strategia di ereditarietà per modularità.
----------------------------------------------------------------------------------------------------------------
3. Presentazione Tecnica
   * Implementato in Java, JPA, Hibernate.
   * Database PostgreSQL.
   * Architettura MVC.
   * Entità e DAO per:
     * Means(Autobus e Tram legate a Manteinance);
     * TicketIssue(VendingMachine e AuthorizatedSellers);
     * TicketOffice(Ticket e Subscription legate a User);
     * Draft legata Stop.
----------------------------------------------------------------------------------------------------------------
4. Funzionalità dell'Applicazione
   * Esecuzione di Application.
   * Metodi di emissione per biglietti e abbonamenti.
   * Recupero parco mezzi e tratte.
   * Persistenza su PostgreSQL.
   * Verifica rapida validità abbonamento.
----------------------------------------------------------------------------------------------------------------
5. Punti di Forza e Sfide
   * Integrazione efficiente di funzionalità.
   * Ottimizzazione logiche tratte e percorsi.
   * Generazione automatica di dati fittizi tramite Java Faker.
   * Implementazione Custom Exceptions.
----------------------------------------------------------------------------------------------------------------
6. EXTRA
    * Metodo per il rinnovo dell'abbonamento.
    * Implementazione Lombok.
    * Creazione Entity specifica per salvare gli errori nel database.
    * Scanner per scatenare un errore volontario.
 
