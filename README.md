# message-producer-mglu-oficial

Projeto de uma Plataforma de Comunicação em Java 21 SE que possibilita o agendamento de um envio em 4 canais distintos (SMS,PUSH,WHATSAPP E EMAIL) usando as tecnologias Spring Boot 6.1.5, RabbitMQ, MySQL, JUnit e Docker.

BUILD

Para buildar o projeto, é necessário abrir o código em um editor de texto, e rodar o arquivo docker-compose.yaml na pasta raíz. Esse arquivo irá gerar as instâncias de banco de dados assim como a do rabbitMQ dentro de containers do Docker.
Após esse processo, podemos verificar que a conexão ao banco de dados já deve estar funcional e acessar o RabbitMQ Managament para criar as Exchanges e Queues ( message-exchange, message-queue, message-rout-key) necessárias para a comunicação entre as partes Produtora e Consumidora do nosso projeto.

Para acessar o RabbitMQ, http://localhost:15672/#/
É necessário fazer a criação dos Exchanges e dos Queues dentro do rabbitmq respeitando o código nativo. O rabbitMQ exige a configuração no administrador para funcionar.
Credenciais podem ser encontradas em application.properties

Podemos compilar e rodar o Projeto Java.

PROJETO
Ao abordar o desafio técnico proposto para criarmos uma plataforma de comunicação que utilize do serviço RabbitMq como Mensageria, aonde exista um microsserviço responsável tanto pelo consumidor como pelo produtor e que faça conexão com banco de dados relacional, precisamos entender as decisões que irão ser feitas. 
(OBS: Idealmente, em um desenvolvimento maior é de extrema vantagem desvincularmos ambos da mesma instância Docker, assim como criar uma modelagem de banco de dados que possibilita que cada um se relacionasse com um banco de dados exclusivos. Isso melhora a manutenção na hora de instabilidades, melhora o gerenciamento de desempenho.). Dessa forma, essa API foi criada no padrão Arquitetura de três camadas, respeitando a hierarquia Controller-Service-Repository.

Foram criadas também arquivo de configuração para exceções globais, e um arquivo para inicialização do banco afim de gerar os dados necessários para as tabelas de Enum.
Falando em Enum, a idéia de criar tabelas específicas para o enum se deve a possibilidade de um aumento do fluxo, mudança de escopo, ou algum avanço na complexidade do sistema, pode significar o aumento de estados durante o processo, assim como os canais utilizados. Ou seja, esse dado pode ser algo que virá a escalar e ao normalizar a base, andamos um passo em frente para a indexação de pontos específicos das nossas tabelas, como esses.

Temos o Producer e o Consumer com algumas funções específicas mas basicamente, é enviar uma mensagem a partir de uma regra de negócio e consumi-la a partir de uma regra de negócio da mesma forma. No caso, muito relacionado ao STATUS das mensagens que foram agendadas visto que dependem disso para efetuar a comunicação propriamente dita pelo canal.
