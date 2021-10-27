Feature: Crear turno valido
  Scenario: Crear turno valido

    Given el usuario está en la vista de crear turno
    When el usuario ingresa nombre en crearPanel
    And el usuario ingresa DNI en crearPanel
    And el usuario ingresa Medicx en crearPanel
    And el usuario ingresa monto en crearPanel
    And el usuario cliquea en reservar
    Then aparece un mensaje de turno creado con exito