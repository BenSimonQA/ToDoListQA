let readAllButton = document.getElementById("readAllButton");

readAllButton.onclick = async () => {
  await readAll();
};

document
    .querySelector("form.readOne")
    .addEventListener("submit", function (stop) {
      stop.preventDefault();
      let formElements = document.querySelector("form.readOne").elements;
      console.log(formElements)
      let id=formElements["floatingID"].value;
      if(isNaN(id)){
        readName(id)
      }
      else{
        readOne(id)
      }

    });

    document
    .querySelector("form.delete")
    .addEventListener("submit", function (stop) {
      stop.preventDefault();
      let formElements = document.querySelector("form.delete").elements;
      console.log(formElements)
      let id=formElements["floatingDelID"].value;
      deleteID(id);

    });

    async function deleteID(id) {
      let response = await fetch(`http://localhost:8080/todo/delete/${id}`, {
        method: "delete",
        headers: {
          "Content-type": "application/json; charset=UTF-8",
        },
      });
    
      if (!response.ok) {
        console.log(
          `Looks like there was a problem. Status Code: ${response.status}`
        );
        return;
      }
      alert(`Deleted ID:${id}`)
    }


    async function readOne(id) {
      let response = await fetch(`http://localhost:8080/todo/read/${id}`, {
        method: "get",
        headers: {
          "Content-type": "application/json; charset=UTF-8",
        },
      });
    
      if (!response.ok) {
        console.log(
          `Looks like there was a problem. Status Code: ${response.status}`
        );
        return;
      }
    
      let data = await response.json();
      console.log(data);
    
      let div = document.getElementById("mytable");
  
      var row = `<tr>
                    <td>${data.id}</td>
                    <td>${data.name}</td>
                    <td>${data.description}</td>
                 </tr>`
      div.innerHTML = row;
    }

    async function readName(id) {
      let response = await fetch(`http://localhost:8080/todo/findByName/${id}`, {
        method: "get",
        headers: {
          "Content-type": "application/json; charset=UTF-8",
        },
      });
    
      if (!response.ok) {
        console.log(
          `Looks like there was a problem. Status Code: ${response.status}`
        );
        return;
      }
    
      let data = await response.json();
      console.log(data);
    
      let div = document.getElementById("mytable");
  
      for(var i=0; i < data.length; i++)
      {
          var row = `<tr>
                        <td>${data[i].id}</td>
                        <td>${data[i].name}</td>
                        <td>${data[i].description}</td>
                     </tr>`
          div.innerHTML = row;
      }
    }

    async function readAll() {
      let response = await fetch(`http://localhost:8080/todo/read`, {
      method: "get",
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    });
  
    if (!response.ok) {
      console.log(
        `Looks like there was a problem. Status Code: ${response.status}`
      );
      return;
    }

  
    let data = await response.json();
    console.log(data);


  
    let div = document.getElementById("mytable");
  

    div.innerHTML = '';
    for(var i=0; i < data.length; i++)
    {
      let dataSize = data[i].task
          var row = `<tr>
                        <td>${data[i].id}</td>
                        <td>${data[i].name}</td>
                        <td>${data[i].description}</td>
                        <td>${dataSize.id}</td>
                     </tr>`
        div.innerHTML += row;
    }
  }