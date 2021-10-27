Feature: Crear Turno no valido
  Scenario: Mensaje de error al intentar cargar un monto menor al costo (Efectivo)
    Given el usuario está en la vista de crear turno
    When el usuario ingresa nombre en crearPanel
    And el usuario ingresa DNI en crearPanel
    And el usuario ingresa Medicx en crearPanel
    And el usuario ingresa monto insuficiente en crearPanel
    And el usuario cliquea en reservar
    Then aparece un mensaje de monto insuficiente