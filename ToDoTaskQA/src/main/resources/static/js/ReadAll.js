

function createElement(data){

  for (const q of data) {

          const myDiv = document.querySelector('#myDiv');

          const pid = document.createElement('p');
          const pname = document.createElement('p');
          const button = document.createElement('a')
          const div = document.createElement('div');
          const newLine = document.createElement('br');


        myDiv.className = 'alert alert-danger';          //for styling
        pid.innerHTML ="ID:"+q.id
        pname.innerHTML ="Car make is:"+q.name
        button.innerHTML = "View"
        button.className = "btn btn-danger"
        button.href = "readOne.html?id="+q.id
      div.append(pid, pname, button, newLine);
      myDiv.append(div);
  }
}


fetch('http://localhost:8080/todo/read')
  .then(
    function(response) {
      if (response.status !== 200) {
        console.log('Looks like there was a problem. Status Code: ' +
          response.status);
        return;
      }

      // Examine the text in the response
      response.json().then(function(commentData) {

        console.log(commentData[1])
        console.log(commentData[1].todo)
        
        console.log(commentData[1].todo[0])
        console.log(commentData[1].todo[1])

        // createElement(commentData)
       

        // let table = document.querySelector("table");
        // let data = Object.keys(commentData[0]); // first record in the array pos 0
        
        // createTableHead(table,data);
        // createTableBody(table,commentData);
        
      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });

  function createTableHead(table,data){
      let tableHead= table.createTHead();
      let row = tableHead.insertRow();
      for(let keys of data){
          // console.log("data",data)
          let th = document.createElement("th");
          let text = document.createTextNode(keys);
          th.appendChild(text);
          row.appendChild(th)
        }
      }
      // let th2 = document.createElement("th")
      // let text2 = document.createTextNode("View");
      // th2.appendChild(text2);
      // row.appendChild(th2);
      // let th3 = document.createElement("th")
      // let text3 = document.createTextNode("Delete");
      // th3.appendChild(text3);
      // row.appendChild(th3);

  function createTableBody(table,commentData){
      for(let commentRecord of commentData){
          let row = table.insertRow();
          for(let values in commentRecord){
              let cell = row.insertCell();
              let text = document.createTextNode(commentRecord[values]);
              cell.appendChild(text);
            }
            let newCell = row.insertCell();
            let myViewButton = document.createElement("a");
            let myButtonValue = document.createTextNode("View one")
            myViewButton.className ="btn btn-warning";
            myViewButton.href="readOne.html?id="+commentRecord.id
            myViewButton.appendChild(myButtonValue);
            newCell.appendChild(myViewButton)
          // let newCellDelete = row.insertCell();
          // let myDelButton = document.createElement("button");
          // let myButtonValue1 = document.createTextNode("Delete a record")
          // myDelButton.className ="btn btn-success";
          // myDelButton.onclick = function(){
          //   delStudent(commentRecord.id);return false;
          };
      //     myDelButton.appendChild(myButtonValue1);
      //     newCellDelete.appendChild(myDelButton)
      // }
  }


  document.addEventListener('DOMContentLoaded', ()=>{
    function fetchData() {
        fetch('http://localhost:3000/quotes')
        .then(resp => resp.json())
        .then(data => renderQuotes(data))
      }
      function renderQuotes(data) {
          for (const q of data) {
    //Find the container where we attach everything to
          const quoteUL = document.querySelector('#quote-list');
    //Create all necessary elements
          const quoteLi = document.createElement('li');
          const blockQuote = document.createElement('blockquote');
          const p = document.createElement('p');
          const footer = document.createElement('footer');
          const br = document.createElement('br');
          const hr = document.createElement('hr')
    //Add appropriate classes and ids. Grab data and insert if needed.
          quoteLi.className = 'quote-card';          //for styling
          blockQuote.className = 'blockquote';       //for styling
          p.className = 'mb-0';                      //for styling
          footer.className = 'blockquote-footer';    //for styling
          quoteLi.dataset.id = q.id
    //Grab data and insert it into created elements
          p.innerHTML = q.quote;
          footer.innerHTML = q.author;
    //Append everything to main container
          blockQuote.append(p, footer, br, hr);
          quoteLi.append(blockQuote);
          quoteUL.append(quoteLi);
          }
       }
    //Call the function that will automatically run renderQuote() also 
       fetchData();
    })