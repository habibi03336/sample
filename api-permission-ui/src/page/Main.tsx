import { Row, Col, Card } from "antd";
import APIList from "../container/APIList";
import AuthorizeEntityList from "../container/AuthorizeEntityList";

const APIPermissionLayout = () => {
	return (
		<div
			style={{
				padding: "16px",
				maxHeight: "100vh",
				backgroundColor: "#f0f2f5",
			}}
		>
			<Row gutter={16}>
				<Col span={12}>
					<Card
						style={{
							height: "calc(100vh - 32px)",
							overflow: "auto",
						}}
						bordered={false}
					>
						<AuthorizeEntityList />
					</Card>
				</Col>
				<Col span={12}>
					<Card
						style={{
							height: "calc(100vh - 32px)",
							overflow: "auto",
						}}
						bordered={false}
					>
						<APIList />
					</Card>
				</Col>
			</Row>
		</div>
	);
};

export default APIPermissionLayout;
