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

    document
    .querySelector("form.deleteTask")
    .addEventListener("submit", function (stop) {
      stop.preventDefault();
      let formElements = document.querySelector("form.deleteTask").elements;
      console.log(formElements)
      let id=formElements["floatingDelID"].value;
      deleteTaskID(id);

    });

    async function deleteTaskID(id) {
      let response = await fetch(`http://localhost:8080/task/delete/${id}`, {
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
      div.innerHTML = '';

      if(data.task.length == 0)
      {
        var row = `<tr>
                    <td>${data.id}</td>
                    <td>${data.name}</td>
                    <td>${data.description}</td>
                    <td>No Task</td>
                    <td>No Difficulty</td>
                  </tr>`
        div.innerHTML = row;
      }
      else{
        for(var j=0; j < data.task.length; j++)
        {
          console.log(data.task[j].name);
          var row = `<tr>
                      <td>${data.id}</td>
                      <td>${data.name}</td>
                      <td>${data.description}</td>
                      <td>${data.task[j].name}</td>
                      <td>${data.task[j].difficulty}</td>
                    </tr>`
          div.innerHTML += row;
        }
      }

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
      div.innerHTML = '';

  
      for(var i=0; i < data.length; i++)
      {
        if(data[i].task.length == 0)
        {
          var row = `<tr>
                      <td>${data[i].id}</td>
                      <td>${data[i].name}</td>
                      <td>${data[i].description}</td>
                      <td>No Task</td>
                      <td>No Difficulty</td>
                    </tr>`
          div.innerHTML += row;
        }
        else{
          for(var j=0; j < data[i].task.length; j++)
          {
            console.log(data[i].task[j].name);
            var row = `<tr>
                        <td>${data[i].id}</td>
                        <td>${data[i].name}</td>
                        <td>${data[i].description}</td>
                        <td>${data[i].task[j].name}</td>
                        <td>${data[i].task[j].difficulty}</td>
                      </tr>`
            div.innerHTML += row;
          }
        }
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
      if(data[i].task.length == 0)
      {
        var row = `<tr>
                    <td>${data[i].id}</td>
                    <td>${data[i].name}</td>
                    <td>${data[i].description}</td>
                    <td>No Task</td>
                    <td>No Difficulty</td>
                  </tr>`
        div.innerHTML += row;
      }
      else{
        for(var j=0; j < data[i].task.length; j++)
        {
          console.log(data[i].task[j].name);
          var row = `<tr>
                      <td>${data[i].id}</td>
                      <td>${data[i].name}</td>
                      <td>${data[i].description}</td>
                      <td>${data[i].task[j].name}</td>
                      <td>${data[i].task[j].difficulty}</td>
                    </tr>`
          div.innerHTML += row;
        }
      }
    }
  }