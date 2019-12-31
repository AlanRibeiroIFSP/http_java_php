<?php
    header('Cache-Control: no-cache, must-revalidate'); 
    header('Content-Type: application/json; charset=utf-8');

    //$aDados = json_decode($_POST['nome'], true);
    if (isset($_POST['nome'])=='alan') {

      $nome      = $_POST['nome'];
      $sobrenome = $_POST['sobrenome'];
      $i = $_POST['idade'];
      $i=$i+5;



      echo '{"nome": nome alterado, "sobrenome": Sobrenome_alterado, "idade":'.$i.' }';
    }
    else
    {
      $nome      = $_POST['nome'];
      $sobrenome = $_POST['sobrenome'];
      $i = $_POST['idade'];
      echo '{"nome": '.$nome.', "sobrenome": '.$sobrenome.', "idade":'.$i.' }';
    }
?> 
