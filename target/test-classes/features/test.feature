Feature: Nescafe Resmi Site Testi
  Scenario: Nescafe'nizi Bulun formunu doldurma
    Given Tarayıcıdan "https://www.nescafe.com/tr/" adresine git
    And Menüde buşunan Kahvelerimiz➝Nescafenizi Bulun butonuna tıkla
    And Şimdi Başla butonuna tıkla
    When çıkan formu doldurup adımları tamamlayınca
    Then sonuç sayfasında doğru sonuçların gösterildiğini kontrol et