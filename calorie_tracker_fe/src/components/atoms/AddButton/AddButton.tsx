import { PlusOutlined } from '@ant-design/icons'
import { Button, ButtonProps } from 'antd'
import React from 'react'
import "./AddButton.css"


export default function AddButton(props: ButtonProps) {
  return (
    <Button {...props} className='add-button' type='primary'><PlusOutlined style={{color: "#000000", fontSize: "30px"}}/></Button>
  )
}
