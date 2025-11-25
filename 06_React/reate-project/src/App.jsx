import { useState } from 'react'
import './App.css'
import Head from './components/Head'

const videoList = [{
  title: "[추석특집] 빵빵이의 일상!",
  channelName: "빵빵이의 일상",
  sumbnail: "https://i.ytimg.com/an_webp/PbLxIaXRtnc/mqdefault_6s.webp?du=3000&sqp=CNjtk8kG&rs=AOn4CLBV6yt90L6RYBkRZhZn6YoeIERKtQ",
  logo: "",
  views: "8.3만",
  date: "1개월 전",
},
{
  title: "[추석특집] 빵빵이의 일상!",
  channelName: "빵빵이의 일상",
  sumbnail: "https://i.ytimg.com/an_webp/PbLxIaXRtnc/mqdefault_6s.webp?du=3000&sqp=CNjtk8kG&rs=AOn4CLBV6yt90L6RYBkRZhZn6YoeIERKtQ",
  logo: "",
  views: "8.3만",
  date: "1개월 전",
},
{
  title: "[추석특집] 빵빵이의 일상!",
  channelName: "빵빵이의 일상",
  sumbnail: "https://i.ytimg.com/an_webp/PbLxIaXRtnc/mqdefault_6s.webp?du=3000&sqp=CNjtk8kG&rs=AOn4CLBV6yt90L6RYBkRZhZn6YoeIERKtQ",
  logo: "",
  views: "8.3만",
  date: "1개월 전",
},
{
  title: "[추석특집] 빵빵이의 일상!",
  channelName: "빵빵이의 일상",
  sumbnail: "https://i.ytimg.com/an_webp/PbLxIaXRtnc/mqdefault_6s.webp?du=3000&sqp=CNjtk8kG&rs=AOn4CLBV6yt90L6RYBkRZhZn6YoeIERKtQ",
  logo: "",
  views: "8.3만",
  date: "1개월 전",
},
{
  title: "[추석특집] 빵빵이의 일상!",
  channelName: "빵빵이의 일상",
  sumbnail: "https://i.ytimg.com/an_webp/PbLxIaXRtnc/mqdefault_6s.webp?du=3000&sqp=CNjtk8kG&rs=AOn4CLBV6yt90L6RYBkRZhZn6YoeIERKtQ",
  logo: "",
  views: "8.3만",
  date: "1개월 전",
},
{
  title: "[추석특집] 빵빵이의 일상!",
  channelName: "빵빵이의 일상",
  sumbnail: "https://i.ytimg.com/an_webp/PbLxIaXRtnc/mqdefault_6s.webp?du=3000&sqp=CNjtk8kG&rs=AOn4CLBV6yt90L6RYBkRZhZn6YoeIERKtQ",
  logo: "",
  views: "8.3만",
  date: "1개월 전",
},
{
  title: "[추석특집] 빵빵이의 일상!",
  channelName: "빵빵이의 일상",
  sumbnail: "https://i.ytimg.com/an_webp/PbLxIaXRtnc/mqdefault_6s.webp?du=3000&sqp=CNjtk8kG&rs=AOn4CLBV6yt90L6RYBkRZhZn6YoeIERKtQ",
  logo: "",
  views: "8.3만",
  date: "1개월 전",
},]

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      {/* <Hello />
      <Hello />
      <Hello /> */}
      {/* <Head type="h2"/> */}
      {/* <Head>
        <h3>무엇을 도와드릴까요?</h3>
      </Head> */}
      <Videos
    </>
  )
}

export default App
