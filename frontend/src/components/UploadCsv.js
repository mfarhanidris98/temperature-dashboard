import React from "react";
import { parse } from "papaparse";

export default function App() {
  const [highlighted, setHighlighted] = React.useState(false);
  const [csv, setCsv] = React.useState([
    {},
  ]);

  return (
    <div>
      <h1 className=" text-4xl">Upload asdfasdfCSV File Here</h1>
      <div
        className={`p-6 my-2 mx-auto max-w-md border-2 ${highlighted ? "border-green-600 bg-green-100" : "border-gray-600"
          }`}
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
        DROP HERE
      </div>


      <ul>
        {csv.map((csv) => (
          <li key={csv.time}>
            <strong>{Number(csv.time)}</strong>: {(Number(csv.temperature1) == '0' ? Number(csv.temperature2) : Number(csv.temperature1))}
          </li>
        ))}
        {console.log(csv)}
        {localStorage.setItem("csv", csv)}
      </ul>
    </div>
  );
}

// module.exports = { csv };