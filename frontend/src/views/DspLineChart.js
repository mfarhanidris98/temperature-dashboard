import { React, useState, useEffect } from 'react';
import CanvasJSReact from '../assets/canvasjs.react';
import '../App.css';
import UploadCsv from '../components/UploadCsv';
import { parse } from "papaparse";

var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;
const csv = require('../components/UploadCsv');

const toDate = (num) => {
  return num.toLocaleString();
}

function DspLineChart() {
  const [highlighted, setHighlighted] = useState(false);
  const [csv, setCsv] = useState([
    {
      uuid: "", empty: "", time: '', empty2: '', empty3: '', temperature1: '', temperature2: ''
    },
  ]);
  // const [content, setContent] = useState("");
  // useEffect(() => {
  //   getCsv(csv).then(
  //     (csv) => {
  //       setContent(csv)
  //     })
  // })
  const options = {
    theme: "dark2",
    animationEnabled: true,
    zoomEnabled: true,
    title: {
      text: "Temperature"
    },
    axisX: {
      title: "Date",
      crosshair: {
        enabled: true,
        snapToDataPoint: true
      }
    },
    axisY: {
      title: "Temperature (in °C)",
      suffix: "°C",
      crosshair: {
        enabled: true,
        snapToDataPoint: true
      }
    },
    data: [{
      type: "scatter",
      markerSize: 10,
      toolTipContent: "Temperature: {x}°C Date: {y}",
      dataPoints: [
        // { x: 1, y: 64 },
        // { x: 2, y: 61 },
        // { x: 3, y: 64 },
        // { x: 4, y: 62 },
        // { x: 5, y: 64 },
        // { x: 6, y: 60 },
        // { x: 7, y: 58 },
        // { x: 8, y: 59 },
        // { x: 9, y: 53 },
        // { x: 10, y: 54 },
        // { x: 11, y: 61 },
        // { x: 12, y: 60 },
        // { x: 13, y: 55 },
        // { x: 14, y: 60 },
        // { x: 15, y: 56 },
        // { x: 16, y: 60 },
        // { x: 17, y: 59.5 },
        // { x: 18, y: 63 },
        // { x: 19, y: 58 },
        // { x: 20, y: 54 },
        // { x: 21, y: 59 },
        // { x: 22, y: 64 },
        // { x: 23, y: 59 }
        { x: 1635730000000, y: 26.35 },
        { x: 1648800000000, y: 22.799999 },
        { x: 1647010000000, y: 25.6 },

        // csv.map((csv) => (
        //   <li key={csv.time}>
        //     <strong>{Number(csv.time)}</strong>: {csv.temperature}
        //   </li>
        // ))

      ]
    }]
  }

  return (
    <div className="container">
      <div className=''>
        <div className=''>
          <div
            style={{
              border: "3px solid black",
              padding: "20px",
              margin: "20px"
            }}
            className=""
            onDragEnter={() => {
              setHighlighted(true);
            }}
            onDragLeave={() => {
              setHighlighted(false);
            }}
            onDragOver={(e) => {
              e.preventDefault();
            }}
            onDrop={(e) => {
              e.preventDefault();
              setHighlighted(false);

              Array.from(e.dataTransfer.files)
                .filter((file) => file.type === "text/csv")
                .forEach(async (file) => {
                  const text = await file.text();
                  const result = parse(text, { header: true });
                  setCsv((existing) => [...existing, ...result.data]);
                });
            }}
          >
            DRAG AND DROP CSV FILE HERE
          </div>
        </div>
      </div>

      <table className="table table-sm table-dark">
        <thead>
          <tr>
            <th scope="col">Time</th>
            <th scope="col">Temperature</th>
          </tr>
        </thead>
        <tbody>
          {csv.map((csv) => (
            <tr key={csv.time}>
              <td>{new Date(Number(csv.time)).toLocaleString()}</td>
              <td>{(Number(csv.temperature1) == '0' ? Number(csv.temperature2) : Number(csv.temperature1))}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <CanvasJSChart options={options}
      /* onRef={ref => this.chart = ref} */
      />
      {/*You can get reference to the chart instance as shown above using onRef. This allows you to access all chart properties and methods*/}
    </div >
  );
}

export default DspLineChart;
