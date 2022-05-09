import React, { useState, useEffect } from 'react';
import styles from "../assets/table.css";

export default function CmpUploadCsv() {
  const [file, setFile] = useState();
  const [array, setArray] = useState([]);

  const fileReader = new FileReader();

  const handleOnChange = (e) => {
    setFile(e.target.files[0]);
  };

  const csvFileToArray = string => {
    const csvHeader = ["uuid", "mode", "timestamp", "temperature"];
    let csvRows = string;

    // while (csvRows.indexOf("\n") != -1) {
    // while (array.length < 10000) {
    for (let i = 0; i < 10000; i++) {
      let values = csvRows.slice(0, csvRows.indexOf("\n") + 1).split(",");

      // console.log(values);
      const obj = csvHeader.reduce((object, header, index) => {
        while (values[index] == "") {
          index++;
        }
        object[header] = values[index];
        // setArray(array => [...array, object]);
        return object;
      }, {});
      console.log(csvRows.length);
      setArray(array => [...array, obj]);
      csvRows = csvRows.slice(csvRows.indexOf("\n") + 1);
    }
    Object.keys(array).map(function (key, index) {
      console.log(key);
      if (key === 'timestamp')
        array[key] = new Date(array[index]);

    });
  };

  const handleOnSubmit = (e) => {
    e.preventDefault();

    if (file) {
      fileReader.onload = function (event) {
        const text = event.target.result;
        csvFileToArray(text);
      };

      fileReader.readAsText(file);
    }
  };

  const headerKeys = Object.keys(Object.assign({}, ...array));

  const calculateRange = (data, rowsPerPage) => {
    const range = [];
    const num = Math.ceil(data.length / rowsPerPage);
    let i = 1;
    for (let i = 1; i <= num; i++) {
      range.push(i);
    }
    return range;
  };

  const sliceData = (data, page, rowsPerPage) => {
    return data.slice((page - 1) * rowsPerPage, page * rowsPerPage);
  };

  const useTable = (data, page, rowsPerPage) => {
    const [tableRange, setTableRange] = useState([]);
    const [slice, setSlice] = useState([]);

    useEffect(() => {
      const range = calculateRange(data, rowsPerPage);
      setTableRange([...range]);

      const slice = sliceData(data, page, rowsPerPage);
      console.log(slice.length);
      setSlice([...slice]);
    }, [data, setTableRange, page, setSlice]);

    return { slice, range: tableRange };
  };

  const TableFooter = ({ range, setPage, page, slice }) => {
    useEffect(() => {
      if (slice.length < 1 && page !== 1) {
        setPage(page - 1);
      }
    }, [slice, page, setPage]);
    return (
      <div className={styles.tableFooter}>
        {range.map((el, index) => (
          <button
            key={index}
            className={`${styles.button} ${page === el ? styles.activeButton : styles.inactiveButton
              }`}
            onClick={() => setPage(el)}
          >
            {el}
          </button>
        ))}
      </div>
    );
  };

  const Table = ({ data, rowsPerPage }) => {
    const [page, setPage] = useState(1);
    const { slice, range } = useTable(data, page, rowsPerPage);
    console.log(slice);

    return (
      <>
        <table className={styles.table}>
          <thead className={styles.tableRowHeader}>
            <tr>
              <th className={styles.tableHeader}>uuid</th>
              <th className={styles.tableHeader}>mode</th>
              <th className={styles.tableHeader}>timestamp</th>
              <th className={styles.tableHeader}>temperature</th>
            </tr>
          </thead>
          <tbody>
            {slice.map((el) => (
              <tr className={styles.tableRowItems} key={el.id}>
                <td className={styles.tableCell}>{el.uuid}</td>
                <td className={styles.tableCell}>{el.mode}</td>
                <td className={styles.tableCell}>{el.timestamp}</td>
                <td className={styles.tableCell}>{el.temperature}</td>
              </tr>
            ))}
          </tbody>
        </table>
        <TableFooter range={range} slice={slice} setPage={setPage} page={page} />
      </>
    );
  };

  console.log(array);

  return (
    <div style={{ textAlign: "center" }}>
      <h1>CSV Import</h1>
      <form>
        <input
          id={"csvFileInput"}
          type={"file"}
          accept={".csv"}
          onChange={handleOnChange}
        />
        <button
          onClick={(e) => {
            handleOnSubmit(e);
          }}
        >Import CSV
        </button>
      </form>
      <br />

      <Table data={array} rowsPerPage={100} />

      {/* <table>
        <thead>
          <tr key={"header"}>
            {headerKeys.map((key) => (
              <th>{key}</th>
            ))}
          </tr>
        </thead>

        <tbody>
          {array.map((item, index) => (
            <tr key={item.index}>
              {Object.values(item).map((val) => (
                <td>{val}</td>
              ))}
            </tr>
          ))
          }
        </tbody>
      </table> */}

    </div>
  );
}