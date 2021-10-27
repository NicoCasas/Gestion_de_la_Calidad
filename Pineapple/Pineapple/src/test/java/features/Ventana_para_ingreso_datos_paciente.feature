Feature: Ventana para ingreso de datos del paciente
  Scenario: Pantalla principal

    Given el usuario está en la pantalla principal
    When el usuario cliquea solicitar turno
    Then aparece la ventana para ingresar los datos del turno