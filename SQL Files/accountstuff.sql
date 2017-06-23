Insert into account(acct_num, balance, user_name) values ('101', '20000.00','user1');
Insert into account(acct_num, balance, user_name) values ('100', '15000.00','user2');

Insert into transaction(acct_num,action,amt,reason) values ('101','Deposit','20000.00', 'Initial Deposit');
Insert into transaction(acct_num,action,amt,reason) values ('100','Deposit','15000.00', 'Initial Deposit');

desc transaction;

select * from account;