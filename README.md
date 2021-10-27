<h1>Projeto final da disciplina de JAVA 1</h1>
  
  
<h3><b>Sistema Bancário</b><br></h3>
O trabalho final da disciplina de Java 1 ( orientação a objeto ) consiste em uma atividade continuada paralela.
O objetivo deste trabalho é promover o desenvolvimento de um pequeno sistema bancário
utilizando os tópicos apresentados e desenvolvidos na disciplina. 


<br><h4><b>Requisitos Mínimos:</b></h4><br>
<b>Classes<br></b>
O sistema deverá ter as seguintes classes obrigatoriamente. (Podendo ter classes adicionais
perante a necessidade descoberta por cada grupo) :<br>
  
● Cliente<br>
● Conta<br>
● Conta Corrente<br>
● Conta Poupança<br>
● Funcionário<br>
 &nbsp; &nbsp; ○ Gerente<br>
 &nbsp; &nbsp; ○ Diretor<br>
 &nbsp; &nbsp; ○ Presidente<br>
● SistemaInterno ( Classe main )<br><br>
  
  
<b>Atributos</b><br>
● Cliente deve ter os atributos de senha e CPF para que possam logar no sistema interno.</br>
● Conta, que deverá ser uma classe abstrata, deve conter os atributos de CPF do titular,
para relacionar a conta com o usuário logado no sistema, e o saldo. Adicionalmente, a
conta deve ter um atributo identificador da agência.</br>
● Conta Corrente e Conta Poupança, que herdarão os atributos e métodos de Conta,
devem conter um atributo “tipo” para identificação do tipo de conta.</br>
● Funcionário ( abstrata ), deve conter atributos também de CPF e senha para que
possam logar no sistema. Um atributo “cargo” também deve existir para identificar qual
é o cargo daquele funcionário. Este atributo pode existir na própria classe Funcionario
ou diretamente em suas classes filhas.</br>
● Gerente, que estenderá de Funcionário deve ter um atributo de identificação da agência
que é responsável por gerir.<br><br>

<b>Características de Funcionamento</b><br>
Este sistema será executado como um menu interativo no console.
Na versão final (a ser entregue) é esperado que o sistema seja populado com os valores
referentes aos clientes, contas e funcionários através da leitura de um arquivo de texto. 
Para testes iniciais, entretanto, valores podem ser atribuídos diretamente.<br><br>


<b>Passos de Execução</b><br>
No menu inicial o usuário deverá fornecer seu CPF e senha para logar.
O sistema deve ser capaz de identificar, no momento do login se o usuário é um Cliente,
Gerente, Diretor ou Presidente.<br><br>

<b>Caso 1 :</b><br>
Caso o usuário seja um Cliente, o sistema fornecerá um menu com opções de :<br>
1. Movimentações na Conta<br>
a. Saque<br>
b. Depósito<br>
c. Transferência para outra conta<br>
2. Relatórios<br>
a. Saldo. O sistema deverá imprimir o saldo na tela do terminal;<br>
b. Relatório de tributação da conta corrente<br>
i. O relatório de tributação deverá apresentar o total gasto nas operações
até o momento do relatório.<br>
ii. Adicionalmente deverão ser informados os valores que o banco cobra por
cada operação bancária;<br>
iii. Para cada saque será cobrado o valor de R$0.10 (dez centavos);<br>
iv. Para cada depósito será cobrado o valor de R$0.10 (dez centavos); v.<br>
Para cada transferência será cobrado o valor de R$0.20 (dez centavos) que
deverá ser cobrado apenas do remetente;<br>
c. Relatório de Rendimento da poupança<br>
i. Retorna uma simulação do valor de rendimento da poupança no prazo<br>
informado. Neste caso o cliente deverá informar o valor em dinheiro e a
quantidade de dias que pretende simular. O sistema deverá informar o
rendimento desse dinheiro para o prazo informado;
d. Desafio:<br>
i. Criar uma classe seguro de vida que possa ser contratado pelo cliente onde
o mesmo informa o valor que será segurado. No ato da contratação será
debitado 20% do valor contratado como tributo do seguro;<br>
ii. Incluir no relatório de tributação o valor referente ao seguro de vida, caso
este cliente possua estas informações.<br><br>

<b>Caso 2 :</b><br>
Caso o usuário seja um Funcionário no cargo de Gerente o sistema fornecerá um menu com
opções de :<br>
1. Movimentações e Informações da Conta<br>
a. Saque<br>
b. Depósito<br>
c. Transferência para outra conta<br>
2. Relatórios<br>
a. Saldo. O sistema deverá imprimir o saldo na tela do terminal;<br>
b. Relatório de tributação da conta corrente<br>
i. O relatório de tributação deverá apresentar o total gasto nas operações
até o momento do relatório.<br>
ii. Adicionalmente deverão ser informados os valores que o banco cobra por
cada operação bancária;<br>
iii. Para cada saque será cobrado o valor de R$0.10 (dez centavos);<br>
iv. Para cada depósito será cobrado o valor de R$0.10 (dez centavos); v.<br>
Para cada transferência será cobrado o valor de R$0.20 (dez centavos) que
deverá ser cobrado apenas do remetente;
c. Relatório de Rendimento da poupança<br>
i. Retorna uma simulação do valor de rendimento da poupança no prazo
informado. Neste caso o cliente deverá informar o valor em dinheiro e a
quantidade de dias que pretende simular. O sistema deverá informar o
rendimento desse dinheiro para o prazo informado;<br>
d. Relatório do número contas na mesma agência em que este gerente
trabalha<br><br>


<b>Caso 3 :</b><br>
Caso o usuário seja um Funcionário no cargo de Diretor o sistema fornecerá um menu com
todas as opções anteriores, adicionando :<br>
1. Relatórios<br>
a. Relatório com as informações de Nome, CPF e Agência de todos os
clientes do sistema em ordem alfabética<br><br>

<b>Caso 4 :</b><br>
Por fim, caso o usuário acessando o sistema seja um Funcionário no cargo de Presidente o
sistema fornecerá um menu com todas as opções anteriores, adicionando :<br>
1. Relatórios<br>
a. Relatório com o valor total do capital armazenado no banco.<br>
Restrições :<br>
Toda operação bancária (Saque, depósito e transferência) deverá ser registrada em um arquivo
de texto de saída que armazena as operações realizadas durante aquela execução do sistema.
Todo relatório gerado deve ser registrado em um arquivo texto de saída individual.
O sistema deve realizar ao menos um tratamento de erros personalizado. Sugestão : Caso
um cliente tente realizar um depósito com valor indevido ( valores negativos ).

