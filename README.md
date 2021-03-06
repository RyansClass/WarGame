# WarGame
codecamp subject

# 概要

ゲームは以下の内容で進行します。

1．対戦はプレイヤーとコンピュータ(CPU)の1対1の対戦です

2．使用するカードはスペードとダイヤの2〜10,J,Q,K,Aのみの計26枚とします

3．使用するカードをシャッフルし、プレイヤーとCPUに均等に配布します。配られたカードの内容はプレイ ヤーには見えません

4．以下の手順でゲームを進行します

4.1.プレイヤーとCPUは、プレイヤーの合図で手札の中から一番上の1枚を同時に場に出します

4.2.場に置かれたカードを開いて数が高い方が勝ち（2が一番弱く、そこから数字が高い方が強くなり、Aは最も強いカードです。マークは関係なし）となり、場に置かれたカード2枚を獲得します

4.3.同点の場合は、出されたカードは場に蓄えられ、次回以降の勝負で勝った方が総取りします。ゲーム終了時点で場にあるカードは破棄されます

4.4.「4.1」〜「4.3」を1ターンとして、プレイヤーまたはCPUの手札がなくなるまでターンを繰り返します。終了した時点で獲得した札が多い方が勝者となります

# 要件

以下の要件を満たすようにしてください。

### 1.ゲーム開始後、それぞれの持ち札を表示して、札を切るか、中断するかを選択させる

【表示結果例】  
- - -  
\### 第1回戦 ###  
場に積まれた札:0枚  
CPUの持ち札:13枚, 獲得した札:0枚  
あなたの持ち札:13枚, 獲得した札:0枚  
札を切りますか? (d:札を切る, q:中断) >  
- - -  

### 2.各ターンのはじめに、「何ターン目か（例、第1回戦）」「場に積まれた札の枚数」「CPUの手札の枚数と獲得したカードの枚数」「プレイヤーの手札の枚数と獲得したカードの枚数」をそれぞれ表示する

【表示結果例】  
- - -  
\### 第1回戦 ###  
場に積まれた札:0枚  
CPUの持ち札:13枚, 獲得した札:0枚  
あなたの持ち札:13枚, 獲得した札:0枚  
札を切りますか? (d:札を切る, q:中断) > d  
CPUが切った札:[スペード5]  
あなたが切った札:[ダイア9]  
あなたが獲得しました!  

\### 第2回戦 ###  
場に積まれた札:0枚  
CPUの持ち札:12枚, 獲得した札:0枚  
あなたの持ち札:12枚, 獲得した札:2枚  
札を切りますか? (d:札を切る, q:中断) >  
- - -

### 3.各ターンのはじめに、ゲームを中断できる。ゲームを中断した場合、現在の状態をファイルに保存する

【表示結果例】  
- - -  
\### 第1回戦 ###  
場に積まれた札:0枚  
CPUの持ち札:13枚, 獲得した札:0枚  
あなたの持ち札:13枚, 獲得した札:0枚  
札を切りますか? (d:札を切る, q:中断) > d  
CPUが切った札:[スペード5]  
あなたが切った札:[ダイア9]  
あなたが獲得しました!

\### 第2回戦 ###  
場に積まれた札:0枚  
CPUの持ち札:12枚, 獲得した札:0枚  
あなたの持ち札:12枚, 獲得した札:2枚  
札を切りますか? (d:札を切る, q:中断) > q  
ゲームを中断します  
- - -  

### 4.次回のゲーム起動時に、ゲームの中断結果ファイルが存在する場合は、続きをするか、新規ゲームをするかの再開を選択できる。もし「y(続きをするか)」または「n(新規ゲーム)」の文字以外が入力された場合、エラーメッセージを表示させて（例、yまたはnの文字を入力してください）、再度、再開の選択を表示する。 また、ゲームが最後まで終了した場合は、次回のゲーム開始時に、続きをするか、新規ゲームをするかの再開の選択を表示しない

【表示結果例】  
- - -  
中断したゲームを再開しますか? [Yes/No] >y  
中断したゲームを再開します。  

\### 第2回戦 ###  
場に積まれた札:0枚  
CPUの持ち札:12枚, 獲得した札:0枚  
あなたの持ち札:12枚, 獲得した札:2枚  
札を切りますか? (d:札を切る, q:中断) >  

※入力文字が不正の場合  
中断したゲームを再開しますか? [Yes/No] >a  
中断したゲームを再開しますか? [Yes/No] >  
- - -  

### 5.カードを出すタイミングは、プレイヤーが入力する。このときに、入力された文字によって、カードを切るか、または中断するかを選択する。もし「d(札を切る)」または「q(中断)」の文字以外が入力された場合、エラーメッセージを表示させて（例、d(札を切る)、またはq(中断)の文字を入力してください）、再度、札を切るか、中断するかの選択を表示する

【表示結果例】  
- - -  
\### 第1回戦 ###  
場に積まれた札:0枚  
CPUの持ち札:13枚, 獲得した札:0枚  
あなたの持ち札:13枚, 獲得した札:0枚  
札を切りますか? (d:札を切る, q:中断) > a  
dまたはqの文字を入力してください  
札を切りますか? (d:札を切る, q:中断) >  
- - -  

### 6.カードを切り終えたら、プレイヤーとCPUが切ったカードの種類と数値をそれぞれ表示し、どちらが札を獲得したのか（あるいは引き分けたのか）、分かるように表示する

【表示結果例】  
- - -  
※プレイヤーが獲得した場合  
\### 第1回戦 ###  
場に積まれた札:0枚  
CPUの持ち札:13枚, 獲得した札:0枚  
あなたの持ち札:13枚, 獲得した札:0枚  
札を切りますか? (d:札を切る, q:中断) > d  
CPUが切った札:[スペード5]  
あなたが切った札:[ダイア9]  
あなたが獲得しました!  

\### 第2回戦 ###  
場に積まれた札:0枚  
CPUの持ち札:12枚, 獲得した札:0枚  
あなたの持ち札:12枚, 獲得した札:2枚  
札を切りますか? (d:札を切る, q:中断) >  

※CPUが獲得した場合  
\### 第1回戦 ###  
場に積まれた札:0枚  
CPUの持ち札:13枚, 獲得した札:0枚  
あなたの持ち札:13枚, 獲得した札:0枚  
札を切りますか? (d:札を切る, q:中断) > d  
CPUが切った札:[スペードK]  
あなたが切った札:[ハート2]  
CPUが獲得しました!  

\### 第2回戦 ###  
場に積まれた札:0枚  
CPUの持ち札:12枚, 獲得した札:2枚  
あなたの持ち札:12枚, 獲得した札:枚  
札を切りますか? (d:札を切る, q:中断) >  

※引き分けの場合  
\### 第1回戦 ###  
場に積まれた札:0枚  
CPUの持ち札:13枚, 獲得した札:0枚  
あなたの持ち札:13枚, 獲得した札:0枚  
札を切りますか? (d:札を切る, q:中断) > d  
CPUが切った札:[スペード5]  
あなたが切った札:[ダイヤ5]  
引き分けです。  

\### 第2回戦 ###  
場に積まれた札:2枚  
CPUの持ち札:12枚, 獲得した札:0枚  
あなたの持ち札:12枚, 獲得した札:0枚  
札を切りますか? (d:札を切る, q:中断) >  
- - -  

### 7.プレイヤーが勝った場合は、プレイヤーの獲得札に場の札を追加する

### 8.CPUが勝った場合は、CPUの獲得札に場の札を追加する

### 9.引き分けの場合は、場の札は場に積んだままにして、次回の勝者が決まるまで持ち越し、次回の勝者側に場に積んだ札を追加する

【表示結果例】  
- - -  
\### 第1回戦 ###  
場に積まれた札:0枚  
CPUの持ち札:13枚, 獲得した札:0枚  
あなたの持ち札:13枚, 獲得した札:0枚  
札を切りますか? (d:札を切る, q:中断) > d  
CPUが切った札:[スペード5]  
あなたが切った札:[ダイヤ5]  
引き分けです。  

\### 第2回戦 ###  
場に積まれた札:2枚  
CPUの持ち札:12枚, 獲得した札:0枚  
あなたの持ち札:12枚, 獲得した札:0枚  
札を切りますか? (d:札を切る, q:中断) >d  
CPUが切った札:[クローバ7]  
あなたが切った札:[ハートJ]  
あなたが獲得しました!  

\### 第3回戦 ###  
場に積まれた札:0枚  
CPUの持ち札:11枚, 獲得した札:0枚  
あなたの持ち札:11枚, 獲得した札:4枚  
札を切りますか? (d:札を切る, q:中断) >  
- - -  

### 10.プレイヤーとCPU双方の手札が残っている場合は、つづけて次のターンを実行する

### 11.プレイヤーあるいはCPUの手札がなくなった場合、試合終了になる。試合の結果（「CPUの総獲得札数」「プレイヤーの総獲得札数」「プレイヤーが勝った、負けた、または引き分け」）を表示する。最後のターンが引き分けで場に札が残っているときは、場の札を破棄する

【表示結果例】  
- - -  
\### 第13回戦 ###  
場に積まれた札:0枚  
CPUの持ち札:1枚, 獲得した札:12枚  
あなたの持ち札:1枚, 獲得した札:12枚  
札を切りますか? (d:札を切る, q:中断) > d  
CPUが切った札:[ハートQ]  
あなたが切った札:[ダイヤA]  
あなたが獲得しました!  

\### 最終結果 ###  
CPUが獲得した札:12枚  
あなたが獲得した札:14枚  
あなたが勝ちました、おめでとう!  
- - -  

### 12.試合終了後、これまでの「ゲーム回数」「プレイヤーの勝利回数」「プレイヤーの最大カード獲得枚数（プレイヤーが勝利したときのみ）」を、CSVファイルに記録する

※記録例は game_result.csv を参照してください。
